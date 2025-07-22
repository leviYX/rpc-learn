package com.levi.service;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

import java.util.Random;

@Slf4j
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {
    private final Random random = new Random();

    @Override
    public StreamObserver<HelloProto.HelloRequest> hello1(StreamObserver<HelloProto.HelloRespnose> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest helloRequest) {
                log.debug("request message is {} ", helloRequest.getName());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.debug("request message all recive ....");
                responseObserver.onNext(HelloProto.HelloRespnose.newBuilder().setResult("result 1").build());
                responseObserver.onNext(HelloProto.HelloRespnose.newBuilder().setResult("result 2").build());
                responseObserver.onCompleted();
            }
        };
    }

    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloRespnose> responseObserver) {
        if (random.nextInt(100) > 30) {
            //如果随机数 大于 30 抛出一个异常 模拟网络问题
            log.debug("接受到了client的请求 返回 UNAVALIABLE");
            responseObserver.onError(Status.UNAVAILABLE.withDescription("for retry").asRuntimeException());
        } else {
            String name = request.getName();
            System.out.println("name = " + name);

            responseObserver.onNext(HelloProto.HelloRespnose.newBuilder().setResult("this is result").build());
            responseObserver.onCompleted();
        }


    }
}
