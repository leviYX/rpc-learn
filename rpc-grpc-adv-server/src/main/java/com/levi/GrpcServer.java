package com.levi;

import com.levi.interceptor.CustomServerInterceptor;
import com.levi.service.HelloServiceImpl;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws InterruptedException, IOException {
        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(9000);
        serverBuilder.addService(new HelloServiceImpl());
        // 注册自定义拦截器
        serverBuilder.intercept(new CustomServerInterceptor());
        Server server = serverBuilder.build();

        server.start();
        server.awaitTermination();
    }
}

