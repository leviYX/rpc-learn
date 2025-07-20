package com.levi;

import com.google.protobuf.ProtocolStringList;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.List;

// client通过代理对象完成远端对象的调用
public class GrpcClient2 {

    public static void main(String[] args) {
        //1.创建通信的管道
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        //2.获得代理对象 stub进行调用
        try {
            // 我们这里以阻塞的形式调用，也就是一直等返回值回来才往下走
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(managedChannel);
            //3. 完成RPC调用
            //3.1 准备参数
            HelloProto.ManyHelloRequest.Builder builder = HelloProto.ManyHelloRequest.newBuilder();
            // 多值参数要这样添加或者以下标形式
            builder.addAllNames(List.of("levi","tom","jerry"));
            HelloProto.ManyHelloRequest helloRequest = builder.build();
            //3.1 进行功能rpc调用，获取相应的内容，像本地调用那样调用远程服务
            HelloProto.ManyHelloResponse helloResponse = helloService.manyHello(helloRequest);
            ProtocolStringList resultList = helloResponse.getResultList();
            System.out.println("resultList = " + resultList);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            // 4. 关闭通道
            managedChannel.shutdown();
        }
    }
}
