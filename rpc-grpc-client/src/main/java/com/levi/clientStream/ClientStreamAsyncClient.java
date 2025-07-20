package com.levi.clientStream;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * 客户端流模式
 */
public class ClientStreamAsyncClient {

    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        try {
            // 使用异步
            HelloServiceGrpc.HelloServiceStub helloService = HelloServiceGrpc.newStub(managedChannel);

            // rpc调用，构建回调获取服务端数据的回调函数
            StreamObserver<HelloProto.HelloRequest> helloRequestStreamObserver = helloService.cs2s(new StreamObserver<HelloProto.HelloResponse>() {
                @Override
                public void onNext(HelloProto.HelloResponse value) {
                    // 监控响应
                    System.out.println("服务端 响应 数据内容为 " + value.getResult());
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    System.out.println("服务端响应结束 ... ");
                }
            });

            //客户端 发送数据 到服务端  多条数据 ，不定时...
            for (int i = 0; i < 10; i++) {
                HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
                builder.setName("hello " + i);
                HelloProto.HelloRequest helloRequest = builder.build();

                // 发送数据,服务端的响应就是基于上面的回调获取的
                helloRequestStreamObserver.onNext(helloRequest);
                Thread.sleep(1000);
            }

            helloRequestStreamObserver.onCompleted();

            managedChannel.awaitTermination(12, TimeUnit.SECONDS);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            managedChannel.shutdown();
        }
    }
}
