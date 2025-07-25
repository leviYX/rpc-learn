package com.levi.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

/**
 * 自定义服务端拦截器
 */
@Slf4j
public class CustomServerInterceptor implements ServerInterceptor {

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> call, Metadata headers, ServerCallHandler<ReqT, RespT> next) {
        //在服务器端 拦截请求操作的功能 写在这个方法中
        log.debug("服务器端拦截器生效.....");

        //包装ServerCall 处理服务端响应拦截
        CustomServerCall<ReqT,RespT> reqTRespTCustomServerCall = new CustomServerCall<>(call);
        // 包装Listener   处理服务端请求拦截
        CustomServerCallListener<ReqT> reqTCustomServerCallListener =
                new CustomServerCallListener<>(next.startCall(reqTRespTCustomServerCall, headers));
        // return reqTCustomServerCallListener;

        /**
         * 目的：只拦截响应，我们就不需要包装Listener，也就是返回原始的Listener即可。原始的Listener我们是通过
         * next.startCall(reqTRespTCustomServerCall, headers)获取到的。所以继续用next.startCall不操作包装的
         * Listener即可，但是我们要包装响应也就是serverCall，所以返回reqTRespTCustomServerCall。包在原始Listener中
         * 你要是包装请求，那就是需要包装的Listener，不需要就直接next.startCall返回startCall即可。
         */
        return next.startCall(reqTRespTCustomServerCall, headers);
    }
}

/**
 * 复杂服务端拦截器，用于监听服务端req请求的事件，reqTListener就是原始的拦截监听器
 * 对于reqTListener的事件，我们可以在事件触发时，做一些自定义的操作，
 * 本质是对于原始监听器的一个包装增强，包装器模式
 */
@Slf4j
class CustomServerCallListener<ReqT> extends ForwardingServerCallListener.SimpleForwardingServerCallListener<ReqT> {
    protected CustomServerCallListener(ServerCall.Listener<ReqT> delegate) {
        super(delegate);
    }

    @Override
    //准备接受请求数据
    public void onReady() {
        log.debug("onRead Method Invoke，准备好接收客户端数据....");
        super.onReady();
    }

    @Override
    public void onMessage(ReqT message) {
        log.debug("接受到了客户端请求提交的数据，客户端的请求数据是:  {} ", message);
        super.onMessage(message);
    }

    @Override
    public void onHalfClose() {
        log.debug("监听到了 半连接触发这个操作...");
        super.onHalfClose();
    }

    @Override
    public void onComplete() {
        log.debug("服务端 调用onCompleted()触发...");
        super.onComplete();
    }

    @Override
    public void onCancel() {
        log.debug("出现异常后 会调用这个方法... 可以在这里做一些关闭资源的操作");
        super.onCancel();
    }
}

/**
 * 通过自定义的ServerCall 包装原始的ServerCall 增加对于响应拦截的功能
 */
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
        log.debug("response 关闭连接 【close】");
        super.close(status, trailers);
    }
}