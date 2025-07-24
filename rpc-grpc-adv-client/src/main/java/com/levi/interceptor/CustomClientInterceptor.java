package com.levi.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Nullable;

/**
 * 自定义客户端拦截器，需要实现grpc提供的拦截器接口ClientInterceptor
 * 该拦截器在客户端发起请求时被调用，
 * 可以在该拦截器中对请求进行处理，比如添加请求头、修改请求参数等
 */
@Slf4j
public class CustomClientInterceptor implements ClientInterceptor {
    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> method, CallOptions callOptions, Channel next) {
        log.debug("模拟业务处理，这是一个拦截启动的处理 ,统一的做了一些操作 ....");
        /*
         * 拦截器在客户端发起stub的rpc调用之前被调用。处理完之后往下传，把本次调用的一些信息继续往下传
         * 把调用交给grpc，所以需要传递下去调用方法的元信息和一些选项
         * 其实就是拦截器方法的MethodDescriptor<ReqT, RespT> method, CallOptions callOptions
         * 然后往下传是用来发起调用的，底层基于netty，所以需要传递Channel next(这是netty调用的基础连接)
         * 所以需要返回一个ClientCall，封装元信息，然后交给grpc，用来发起调用
         */
        // return next.newCall(method, callOptions);
        /*
         *  如果我们需要用复杂客户端拦截器 ，就需要对原始的ClientCall进行包装
         *  那么这个时候，就不能返回原始ClientCall对象，
         *  应该返回 包装的ClientCall ---> CustomForwardingClientClass
         */
        return new CustomForwardingClientClass<>(next.newCall(method, callOptions));
    }
}

/*
   这个类型增强原始类型 适用于控制 拦截 请求发送各个环节
 */
@Slf4j
class CustomForwardingClientClass<ReqT, RespT> extends ClientInterceptors.CheckedForwardingClientCall<ReqT, RespT> {

    /**
     * 构造器模式，需要实现构造函数，传入原始类型，进行增强类型的包装
     */
    protected CustomForwardingClientClass(ClientCall<ReqT, RespT> delegate) {
        super(delegate);
    }

    /**
     * 开始调用,目的 看一个这个RPC请求是不是可以被发起。比如加一些鉴权等功能来判断是不是可以调用，如果不可以直接
     * 返回responseListener.onClose(Status.INTERNAL, new Metadata());
     * 否则就发起请求delegate().start(responseListener, headers);
     */
    protected void checkedStart(Listener<RespT> responseListener, Metadata headers) throws Exception {
        log.debug("发送请求数据之前的检查.....");
        //真正的去发起grpc的请求
        // 是否真正发送grpc的请求，取决这个start方法的调用，delegate()就是原始类型，可以通过构造函数来看到
        // delegate()就是原始类型那个之前简单调用的ClientCall，这就是装饰器模式
        // delegate().start(responseListener, headers);
        delegate().start(new CustomCallListener<>(responseListener), headers);
    }

    // 真正开始发送消息，netty的发送消息的方法，outBoundBuffer
    @Override
    public void sendMessage(ReqT message) {
        log.info("发送请求数据: {}", message);
        super.sendMessage(message);
    }

    // 指定发送消息的数量，类似批量发送
    @Override
    public void request(int numMessages) {
        log.info("指定发送消息的数量: {}", numMessages);
        super.request(numMessages);
    }

    // 取消请求的时候回调触发
    @Override
    public void cancel(@Nullable String message, @Nullable Throwable cause) {
        log.info("取消请求: {}", message);
        super.cancel(message, cause);
    }

    // 链接半关闭的时候回调触发，请求消息无法发送，但是可以接受响应的消息
    @Override
    public void halfClose() {
        log.info("链接半关闭");
        super.halfClose();
    }

    // 消息发送是否启用压缩
    @Override
    public void setMessageCompression(boolean enabled) {
        log.info("消息发送是否启用压缩: {}", enabled);
        super.setMessageCompression(enabled);
    }

    // 是否可以发送消息，这个在流式里面会调用，一元的不会
    @Override
    public boolean isReady() {
        log.info("是否可以发送消息: {}", super.isReady());
        return super.isReady();
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

    // 这个在流式里面会调用，一元的不会，可以不实现
    @Override
    public void onReady() {
        super.onReady();
    }


    // 这个在流式里面会调用，一元的不会，可以不实现
    @Override
    public void onClose(Status status, Metadata trailers) {
        super.onClose(status, trailers);
    }
}
