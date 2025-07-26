package com.levi;

import com.levi.interceptor.CustomerClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9000)
                .intercept(List.of(new CustomerClientInterceptor()))
                .usePlaintext()
                .build();

        try {
            HelloServiceGrpc.HelloServiceStub helloServiceStub = HelloServiceGrpc.newStub(managedChannel);
            StreamObserver<HelloProto.HelloRequest> requestStreamObserver = helloServiceStub.hello1(new StreamObserver<HelloProto.HelloRespnose>() {
                @Override
                public void onNext(HelloProto.HelloRespnose helloRespnose) {
                    log.info("接收到的服务端响应为{}: ", helloRespnose.getResult());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                // 服务端这边的onCompleted方法会触发这个
                @Override
                public void onCompleted() {
                    log.info("服务端响应完成");
                }
            });
            for (int i = 0; i < 5; i++) {
                requestStreamObserver.onNext(HelloProto.HelloRequest.newBuilder().setName("levi" + i).build());
            }
            // 会触发服务端那边的onCompleted监听事件方法
            requestStreamObserver.onCompleted();
            managedChannel.awaitTermination(5, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            managedChannel.shutdown();
        }
    }
}
