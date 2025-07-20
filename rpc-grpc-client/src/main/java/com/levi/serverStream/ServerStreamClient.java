package com.levi.serverStream;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.Iterator;

/**
 * 服务端流模式
 */
public class ServerStreamClient {

    public static void main(String[] args) {
        //1.创建通信的管道
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        //2.获得代理对象 stub进行调用
        try {
            // 我们这里以阻塞的形式调用，也就是一直等返回值回来才往下走
            HelloServiceGrpc.HelloServiceBlockingStub helloService = HelloServiceGrpc.newBlockingStub(managedChannel);
            //3. 完成RPC调用
            //3.1 准备参数
            HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
            builder.setName("hello");
            HelloProto.HelloRequest helloRequest = builder.build();
            //3.1 进行功能rpc调用，获取相应的内容，像本地调用那样调用远程服务,流式的返回是一个迭代器
            Iterator<HelloProto.HelloResponse> helloResponseIterator = helloService.c2ss(helloRequest);
            // 流式获取
            while (helloResponseIterator.hasNext()) {
                HelloProto.HelloResponse helloResponse = helloResponseIterator.next();
                System.out.println("客户端接收到服务端的响应:" + helloResponse.getResult());
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            // 4. 关闭通道
            managedChannel.shutdown();
        }
    }
}
