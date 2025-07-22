package com.levi;

import com.levi.stream_interceptor.CustomerClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

@Slf4j
public class GrpcClient1 {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000)
                .intercept(new CustomerClientInterceptor())
                .usePlaintext()
                .build();

        try {
            HelloServiceGrpc.HelloServiceStub helloServiceStub = HelloServiceGrpc.newStub(managedChannel);
            StreamObserver<HelloProto.HelloRequest> helloRequestStreamObserver = helloServiceStub.hello1(new StreamObserver<HelloProto.HelloRespnose>() {
                @Override
                public void onNext(HelloProto.HelloRespnose helloRespnose) {
                    log.debug("client 接受到了 服务端的响应 {} ", helloRespnose.getResult());
                }

                @Override
                public void onError(Throwable throwable) {

                }

                @Override
                public void onCompleted() {
                    log.debug("client 接受到了 所有的响应内容 响应结束 ");
                }
            });


            for (int i = 0; i < 3; i++) {
                helloRequestStreamObserver.onNext(HelloProto.HelloRequest.newBuilder().setName("sunshuai " + i).build());
            }
            helloRequestStreamObserver.onCompleted();

            managedChannel.awaitTermination(4, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            managedChannel.shutdown();
        }
    }
}
