package com.levi;

import com.levi.interceptor.CustomClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 9000)
                // .intercept(new CustomClientInterceptor())
                // 可以传递多个拦截器，按照传递顺序执行拦截器
                .intercept(List.of(new CustomClientInterceptor()))
                .usePlaintext()
                .build();

        try {
            HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);
            HelloProto.HelloRequest helloRequest = HelloProto.HelloRequest.newBuilder()
                    .setName("levi")
                    .build();
            HelloProto.HelloRespnose helloRespnose = helloServiceBlockingStub.hello(helloRequest);
            System.out.println("接收到的服务端响应为: " + helloRespnose.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            managedChannel.shutdown();
        }
    }
}
