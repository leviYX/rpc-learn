package com.levi.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomServerInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        //在服务器端 拦截请求操作的功能 写在这个方法中
        log.debug("服务器端拦截器生效.....");
        //默认返回的ServerCall.Listener仅仅能够完成请求数据的监听，单没有拦截功能
        //所以要做扩展，采用包装器设计模式。
        //return next.startCall(call, headers);

        //只拦截请求
        //return new CustomServerCallListener<>(next.startCall(call, headers));

        //只拦截响应
        //CustomServerCall<ReqT,RespT> reqTRespTCustomServerCall = new CustomServerCall<>(call);
        //return next.startCall(reqTRespTCustomServerCall,headers);

        //同时拦截请求 与 响应
        //1. 包装ServerCall 处理服务端响应拦截
        CustomServerCall<ReqT, RespT> reqTRespTCustomServerCall = new CustomServerCall<>(call);
        //2. 包装Listener   处理服务端请求拦截
        CustomServerCallListener<ReqT> reqTCustomServerCallListener = new CustomServerCallListener<>(next.startCall(reqTRespTCustomServerCall, headers));
        return reqTCustomServerCallListener;
    }
}

@Slf4j
class CustomServerCall<ReqT, RespT> extends ForwardingServerCall.SimpleForwardingServerCall<ReqT, RespT> {

    protected CustomServerCall(ServerCall<ReqT, RespT> delegate) {
        super(delegate);
    }

    @Override
    //指定发送消息的数量 【响应消息】
    public void request(int numMessages) {
        log.debug("response 指定消息的数量 【request】");
        super.request(numMessages);
    }

    @Override
    //设置响应头
    public void sendHeaders(Metadata headers) {
        log.debug("response 设置响应头 【sendHeaders】");
        super.sendHeaders(headers);
    }

    @Override
    //响应数据
    public void sendMessage(RespT message) {
        log.debug("response 响应数据  【send Message 】 {} ", message);
        super.sendMessage(message);
    }

    @Override
    //关闭连接
    public void close(Status status, Metadata trailers) {
        log.debug("respnse 关闭连接 【close】");
        super.close(status, trailers);
    }
}


@Slf4j
class CustomServerCallListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
    protected CustomServerCallListener(ServerCall.Listener<ReqT> delegate) {
        super(delegate);
    }

    @Override
    //准备接受请求数据
    public void onReady() {
        log.debug("onRead Method Invoke....");
        super.onReady();
    }

    @Override
    public void onMessage(ReqT message) {
        log.debug("接受到了 请求提交的数据  {} ", message);
        super.onMessage(message);
    }

    @Override
    public void onHalfClose() {
        log.debug("监听到了 半连接...");
        super.onHalfClose();
    }

    @Override
    public void onComplete() {
        log.debug("服务端 onCompleted()...");
        super.onComplete();
    }

    @Override
    public void onCancel() {
        log.debug("出现异常后 会调用这个方法... 关闭资源的操作");
        super.onCancel();
    }
}
