package com.levi.interceptor;

import io.grpc.*;
import lombok.extern.slf4j.Slf4j;

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
        return next.newCall(method, callOptions);
    }
}
