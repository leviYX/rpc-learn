package com.levi.service;

import com.google.protobuf.ProtocolStringList;
import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

// 服务端实现类
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    private static final String RES_PREFIX = "server#";

    /**
     * 双向流 流模式
     * @param responseObserver
     * @return
     */
    @Override
    public StreamObserver<HelloProto.HelloRequest> b2s(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest value) {
                System.out.println("接受到client 提交的消息 "+value.getName());
                responseObserver.onNext(HelloProto.HelloResponse.newBuilder().setResult("response "+value.getName()+" result ").build());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("接受到了所有的请求消息 ... ");
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * 客户端 流模式
     * @param responseObserver
     * @return
     */
    public StreamObserver<HelloProto.HelloRequest> cs2s(StreamObserver<HelloProto.HelloResponse> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest value) {
                System.out.println("接受到了client发送一条消息 " + value.getName());
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onCompleted() {
                System.out.println("client的所有消息 都发送到了 服务端 ....");

                //提供响应：响应的目的：当接受了全部client提交的信息，并处理后，提供相应
                HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
                builder.setResult("this is result");
                HelloProto.HelloResponse helloResponse = builder.build();

                // responseObserver服务端 响应 客户端，你可以在onCompleted一起返回，也可以在onNext一个一个返回，看你自己业务
                responseObserver.onNext(helloResponse);
                responseObserver.onCompleted();
            }
        };
    }

    /**
     * 服务端 流模式
     * @param request
     * @param responseObserver
     */
    @Override
    public void c2ss(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        //1 接受client的请求参数
        String requestName = request.getName();
        //2 做业务处理
        System.out.println("name: " + requestName);
        //3 根据业务处理的结果，提供响应，推送多个给客户端
        for (int i = 0; i < 9; i++) {
            HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
            builder.setResult("服务端处理的结果:" + i);
            HelloProto.HelloResponse helloResponse = builder.build();
            responseObserver.onNext(helloResponse);
            try {
                // 暂停一秒推送数据给客户端
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        responseObserver.onCompleted();
    }

    /**
     * 简单 rpc(一元模式)
     * @param request
     * @param responseObserver
     */
    @Override
    public void manyHello(HelloProto.ManyHelloRequest request, StreamObserver<HelloProto.ManyHelloResponse> responseObserver) {
        //1.接受client的请求参数,我们看到此时就是一个nameList的集合了，因为它被repeated修饰了，当然他的类型是ProtocolStringList，是grpc自己的类型
        ProtocolStringList requestNamesList = request.getNamesList();
        //2.业务处理
        System.out.println("请求参数为:" + requestNamesList);
        // 给返回值的name都加一个前缀
        List<String> responseNamesList = new ArrayList<>();
        for (String requestName : requestNamesList) {
            responseNamesList.add(RES_PREFIX + requestName);
        }

        //3.封装响应
        //3.1 创建相应对象的构造者
        HelloProto.ManyHelloResponse.Builder builder = HelloProto.ManyHelloResponse.newBuilder();
        //3.2 填充数据,多个值要通过addAllResult,或者是下标的方式添加
        builder.addAllResult(responseNamesList);
//        for (int i = 0; i < requestNamesList.size(); i++) {
//            builder.setResult(i, requestNamesList.get(i));
//        }
        //3.3 封装响应
        HelloProto.ManyHelloResponse helloResponse = builder.build();

        // 4. 响应client
        responseObserver.onNext(helloResponse);
        // 5. 响应完成
        responseObserver.onCompleted();
    }

    /*
          1. 接受client提交的参数  request.getParameter()
          2. 业务处理 service+dao 调用对应的业务功能。
          3. 提供返回值
         */
    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloResponse> responseObserver) {
        //1.接受client的请求参数
        String name = request.getName();
        //2.业务处理
        System.out.println("name parameter "+name);
        //3.封装响应
        //3.1 创建相应对象的构造者
        HelloProto.HelloResponse.Builder builder = HelloProto.HelloResponse.newBuilder();
        //3.2 填充数据
        builder.setResult("hello method invoke ok");
        //3.3 封装响应
        HelloProto.HelloResponse helloResponse = builder.build();

        // 4. 响应client
        responseObserver.onNext(helloResponse);
        // 5. 响应完成
        responseObserver.onCompleted();
    }
}
