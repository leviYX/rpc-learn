package com.levi;

import com.google.common.util.concurrent.FutureCallback;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class GrpcFutureClient {
    public static void main(String[] args) {
        //1.创建通信的管道
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9000).usePlaintext().build();
        //2.获得代理对象 stub进行调用
        try {
            // 我们这里以阻塞的形式调用，也就是一直等返回值回来才往下走
            HelloServiceGrpc.HelloServiceFutureStub helloServiceFutureStub = HelloServiceGrpc.newFutureStub(managedChannel);
            //3. 完成RPC调用，我们演示三种，具体选择哪个看你要求
            //3.1 准备参数
            HelloProto.HelloRequest helloRequest = HelloProto.HelloRequest.newBuilder().setName("hello").build();
            //3.1 进行功能rpc调用，获取相应的内容，像本地调用那样调用远程服务
            // 同步阻塞方式调用，阻塞等待future.get()返回结果
            ListenableFuture<HelloProto.HelloResponse> helloResponseListenableFuture = helloServiceFutureStub.hello(helloRequest);

            // syncCall(helloResponseListenableFuture);


            // 异步回调函数方式调用(推荐)
            asyncCall(helloResponseListenableFuture);

            // 异步监听方式调用
            // asyncListener(helloResponseListenableFuture);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            // 4. 关闭通道
            managedChannel.shutdown();
        }
    }

    /**
     * 同步阻塞方式调用，阻塞等待future.get()返回结果
     *
     * @param helloResponseListenableFuture
     * @throws ExecutionException
     * @throws InterruptedException
     */
    private static void syncCall(ListenableFuture<HelloProto.HelloResponse> helloResponseListenableFuture) throws ExecutionException, InterruptedException {
        HelloProto.HelloResponse helloResponse = helloResponseListenableFuture.get();
        System.out.println("result = " + helloResponse.getResult());
    }

    /**
     * 异步回调函数方式调用(推荐)
     *
     * @param helloResponseListenableFuture
     */
    private static void asyncCall(ListenableFuture<HelloProto.HelloResponse> helloResponseListenableFuture) {
        Futures.addCallback(helloResponseListenableFuture, new FutureCallback<HelloProto.HelloResponse>() {
            @Override
            public void onSuccess(HelloProto.HelloResponse result) {
                System.out.println("result.getResult() = " + result.getResult());
            }

            @Override
            public void onFailure(Throwable t) {

            }
        }, Executors.newCachedThreadPool());
    }

    /**
     * 异步监听方式调用
     *
     * @param helloResponseListenableFuture
     */
    private static void asyncListener(ListenableFuture<HelloProto.HelloResponse> helloResponseListenableFuture) {
        helloResponseListenableFuture.addListener(() -> {
            System.out.println("helloResponseListenableFuture 完成");
        }, Executors.newCachedThreadPool());
    }
}
