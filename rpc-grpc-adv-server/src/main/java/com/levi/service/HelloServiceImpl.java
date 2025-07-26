package com.levi.service;

import com.levi.HelloProto;
import com.levi.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HelloServiceImpl extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public StreamObserver<HelloProto.HelloRequest> hello1(StreamObserver<HelloProto.HelloRespnose> responseObserver) {
        return new StreamObserver<HelloProto.HelloRequest>() {
            @Override
            public void onNext(HelloProto.HelloRequest helloRequest) {
                log.debug("客户端的请求消息为:{} ", helloRequest.getName());
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                log.debug("客户端请求的消息全部接收到 ....");
                // 服务端可以发送多条消息给客户端,在全部收到客户端消息之时，你也可以在上面的onNext每收到一条就发送一条，看你业务
                responseObserver.onNext(HelloProto.HelloRespnose.newBuilder().setResult("result 1").build());
                responseObserver.onNext(HelloProto.HelloRespnose.newBuilder().setResult("result 2").build());
                responseObserver.onCompleted();
            }
        };
    }

}
