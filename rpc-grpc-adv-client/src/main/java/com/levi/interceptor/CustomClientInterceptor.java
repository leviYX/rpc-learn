package com.levi.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        log.debug("这是一个拦截启动的处理 ,统一的做了一些操作 ....");
        /*
           如果我们需要用复杂客户端拦截器 ，就需要对原始的ClientCall进行包装
           那么这个时候，就不能反悔原始ClientCall对象，
           应该返回 包装的ClientCall ---> CustomForwardingClientClass
         */
        //return next.newCall(method, callOptions);
        return new CustomForwardingClientClass<>(next.newCall(method, callOptions));
    }
}

/*
   这个类型 适用于控制 拦截 请求发送各个环节
 */
@Slf4j
class CustomForwardingClientClass<ReqT, RespT> extends ClientInterceptors.CheckedForwardingClientCall<ReqT, RespT> {

    protected CustomForwardingClientClass(ClientCall<ReqT, RespT> delegate) {
        super(delegate);
    }

    @Override
    //开始调用
    // 目的 看一个这个RPC请求是不是可以被发起。
    protected void checkedStart(Listener<RespT> responseListener, Metadata headers) throws Exception {
        log.debug("发送请求数据之前的检查.....");
        //真正的去发起grpc的请求
        // 是否真正发送grpc的请求，取决这个start方法的调用
        //delegate().start(responseListener, headers);
        delegate().start(new CustomCallListener<>(responseListener), headers);
    }

    @Override
    //指定发送消息的数量
    public void request(int numMessages) {
        //添加一些功能
        log.debug("request 方法被调用 ....");
        super.request(numMessages);
    }

    @Override
    //发送消息 缓冲区
    public void sendMessage(ReqT message) {
        log.debug("sendMessage 方法被调用... {} ", message);
        super.sendMessage(message);
    }

    @Override
    //开启半连接 请求消息无法发送，但是可以接受响应的消息
    public void halfClose() {
        log.debug("halfClose 方法被调用... 开启了半连接");
        super.halfClose();
    }
}

/*
   用于监听响应，并对响应进行拦截
 */
@Slf4j
class CustomCallListener<RespT> extends ForwardingClientCallListener.SimpleForwardingClientCallListener<RespT> {
    protected CustomCallListener(ClientCall.Listener<RespT> delegate) {
        super(delegate);
    }

    @Override
    public void onHeaders(Metadata headers) {
        log.info("响应头信息 回来了......");
        super.onHeaders(headers);
    }

    @Override
    public void onMessage(RespT message) {
        log.info("响应的数据 回来了.....{} ", message);
        super.onMessage(message);
    }
}
