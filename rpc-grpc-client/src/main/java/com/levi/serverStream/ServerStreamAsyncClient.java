package com.levi.serverStream;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Iterator;
import java.util.concurrent.TimeUnit;

/**
 * 服务端流模式
 */
public class ServerStreamAsyncClient {

    public static void main(String[] args) {
        //1.创建通信的管道
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        //2.获得代理对象 stub进行调用
        try {
            // 我们这里以异步的形式发起服务端流模式的rpc调用
            HelloServiceGrpc.HelloServiceStub helloServiceStub = HelloServiceGrpc.newStub(managedChannel);
            //3. 完成RPC调用
            //3.1 准备参数
            HelloProto.HelloRequest.Builder builder = HelloProto.HelloRequest.newBuilder();
            builder.setName("hello");
            HelloProto.HelloRequest helloRequest = builder.build();
            //3.1 进行功能rpc调用，获取相应的内容，像本地调用那样调用远程服务,异步回调的方式来获取服务端内容
            helloServiceStub.c2ss(helloRequest, new StreamObserver<HelloProto.HelloResponse>() {
                @Override
                public void onNext(HelloProto.HelloResponse value) {
                    //服务端 响应了 一个消息后，需要立即处理的话。把代码写在这个方法中。响应一个监听获取一个
                    System.out.println("服务端每一次响应的信息 " + value.getResult());
                }

                @Override
                public void onError(Throwable t) {

                }

                @Override
                public void onCompleted() {
                    //需要把服务端 响应的所有数据 拿到后，在进行业务处理。
                    System.out.println("服务端响应结束 后续可以根据需要 在这里统一处理服务端响应的所有内容");
                }
            });
            // 正常业务执行
            doTask();
            // 等待服务端响应结束，不然main线程会结束，导致进程结束
            managedChannel.awaitTermination(12, TimeUnit.SECONDS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            // 4. 关闭通道
            managedChannel.shutdown();
        }
    }

    private static void doTask(){
        System.out.println("处理我们自己的业务......start");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("处理我们自己的业务......end");
    }
}
