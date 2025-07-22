package com.levi;

import com.levi.interceptor.CustomClientInterceptor;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class GrpcClient {
    public static void main(String[] args) {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000)
                .intercept(new CustomClientInterceptor())
                .usePlaintext()
                .build();

        try {
            HelloServiceGrpc.HelloServiceBlockingStub helloServiceBlockingStub = HelloServiceGrpc.newBlockingStub(managedChannel);
            HelloProto.HelloRespnose helloRespnose = helloServiceBlockingStub.hello(HelloProto.HelloRequest.newBuilder().setName("xiaohei").build());

            System.out.println("helloRespnose.getResult() = " + helloRespnose.getResult());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            managedChannel.shutdown();
        }
    }
}
