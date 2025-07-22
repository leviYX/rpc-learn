package com.levi.service;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloProto.HelloRequest request, StreamObserver<HelloProto.HelloRespnose> responseObserver) {
        String name = request.getName();
        System.out.println("接收到客户端的参数name = " + name);
        responseObserver.onNext(HelloProto.HelloRespnose.newBuilder().setResult("this is server result").build());
        responseObserver.onCompleted();
    }

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

}
