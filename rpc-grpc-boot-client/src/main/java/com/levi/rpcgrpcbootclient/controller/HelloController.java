package com.levi.rpcgrpcbootclient.controller;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    // 以grpc注解的形式注入客户端stub，可以选择阻塞的还是流的还是异步的啥的，看你自己
    @GrpcClient("grpc-server")
    private HelloServiceGrpc.HelloServiceBlockingStub stub;

    @GetMapping("/hello")
    public String hello() {
        HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
        builder.setName("levi");
        HelloProto.HelloRequest helloRequest = builder.build();
        HelloProto.HelloResponse helloResponse = stub.hello(helloRequest);
        return helloResponse.getResult();
    }

}
