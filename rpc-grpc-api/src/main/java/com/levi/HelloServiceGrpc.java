package com.levi;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 * <pre>
 * 定义服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.73.0)",
    comments = "Source: hello.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class HelloServiceGrpc {

  private HelloServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "HelloService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "hello",
      requestType = com.levi.HelloProto.HelloRequest.class,
      responseType = com.levi.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getHelloMethod() {
    io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse> getHelloMethod;
    if ((getHelloMethod = HelloServiceGrpc.getHelloMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getHelloMethod = HelloServiceGrpc.getHelloMethod) == null) {
          HelloServiceGrpc.getHelloMethod = getHelloMethod =
              io.grpc.MethodDescriptor.<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "hello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("hello"))
              .build();
        }
      }
    }
    return getHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.levi.HelloProto.ManyHelloRequest,
      com.levi.HelloProto.ManyHelloResponse> getManyHelloMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "manyHello",
      requestType = com.levi.HelloProto.ManyHelloRequest.class,
      responseType = com.levi.HelloProto.ManyHelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.levi.HelloProto.ManyHelloRequest,
      com.levi.HelloProto.ManyHelloResponse> getManyHelloMethod() {
    io.grpc.MethodDescriptor<com.levi.HelloProto.ManyHelloRequest, com.levi.HelloProto.ManyHelloResponse> getManyHelloMethod;
    if ((getManyHelloMethod = HelloServiceGrpc.getManyHelloMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getManyHelloMethod = HelloServiceGrpc.getManyHelloMethod) == null) {
          HelloServiceGrpc.getManyHelloMethod = getManyHelloMethod =
              io.grpc.MethodDescriptor.<com.levi.HelloProto.ManyHelloRequest, com.levi.HelloProto.ManyHelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "manyHello"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.ManyHelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.ManyHelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("manyHello"))
              .build();
        }
      }
    }
    return getManyHelloMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getC2ssMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "c2ss",
      requestType = com.levi.HelloProto.HelloRequest.class,
      responseType = com.levi.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getC2ssMethod() {
    io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse> getC2ssMethod;
    if ((getC2ssMethod = HelloServiceGrpc.getC2ssMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getC2ssMethod = HelloServiceGrpc.getC2ssMethod) == null) {
          HelloServiceGrpc.getC2ssMethod = getC2ssMethod =
              io.grpc.MethodDescriptor.<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "c2ss"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("c2ss"))
              .build();
        }
      }
    }
    return getC2ssMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getCs2sMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "cs2s",
      requestType = com.levi.HelloProto.HelloRequest.class,
      responseType = com.levi.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getCs2sMethod() {
    io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse> getCs2sMethod;
    if ((getCs2sMethod = HelloServiceGrpc.getCs2sMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getCs2sMethod = HelloServiceGrpc.getCs2sMethod) == null) {
          HelloServiceGrpc.getCs2sMethod = getCs2sMethod =
              io.grpc.MethodDescriptor.<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "cs2s"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("cs2s"))
              .build();
        }
      }
    }
    return getCs2sMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getB2sMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "b2s",
      requestType = com.levi.HelloProto.HelloRequest.class,
      responseType = com.levi.HelloProto.HelloResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest,
      com.levi.HelloProto.HelloResponse> getB2sMethod() {
    io.grpc.MethodDescriptor<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse> getB2sMethod;
    if ((getB2sMethod = HelloServiceGrpc.getB2sMethod) == null) {
      synchronized (HelloServiceGrpc.class) {
        if ((getB2sMethod = HelloServiceGrpc.getB2sMethod) == null) {
          HelloServiceGrpc.getB2sMethod = getB2sMethod =
              io.grpc.MethodDescriptor.<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "b2s"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.levi.HelloProto.HelloResponse.getDefaultInstance()))
              .setSchemaDescriptor(new HelloServiceMethodDescriptorSupplier("b2s"))
              .build();
        }
      }
    }
    return getB2sMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static HelloServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceStub>() {
        @java.lang.Override
        public HelloServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceStub(channel, callOptions);
        }
      };
    return HelloServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports all types of calls on the service
   */
  public static HelloServiceBlockingV2Stub newBlockingV2Stub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingV2Stub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingV2Stub>() {
        @java.lang.Override
        public HelloServiceBlockingV2Stub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceBlockingV2Stub(channel, callOptions);
        }
      };
    return HelloServiceBlockingV2Stub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static HelloServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceBlockingStub>() {
        @java.lang.Override
        public HelloServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceBlockingStub(channel, callOptions);
        }
      };
    return HelloServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static HelloServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<HelloServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<HelloServiceFutureStub>() {
        @java.lang.Override
        public HelloServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new HelloServiceFutureStub(channel, callOptions);
        }
      };
    return HelloServiceFutureStub.newStub(factory, channel);
  }

  /**
   * <pre>
   * 定义服务
   * </pre>
   */
  public interface AsyncService {

    /**
     * <pre>
     * 简单rpc,参数为HelloRequest类型，返回类型为HelloResponse 
     * </pre>
     */
    default void hello(com.levi.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     * 多值rpc,参数为ManyHelloRequest类型，返回类型为ManyHelloResponse 
     * </pre>
     */
    default void manyHello(com.levi.HelloProto.ManyHelloRequest request,
        io.grpc.stub.StreamObserver<com.levi.HelloProto.ManyHelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getManyHelloMethod(), responseObserver);
    }

    /**
     * <pre>
     * 服务端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    default void c2ss(com.levi.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getC2ssMethod(), responseObserver);
    }

    /**
     * <pre>
     * 客户端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloRequest> cs2s(
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getCs2sMethod(), responseObserver);
    }

    /**
     * <pre>
     * 双向流式rpc,参数为HelloRequest类型，返回类型为HelloResponse,请求参数和响应参数都是stream表示请求和响应两端是双向流
     * </pre>
     */
    default io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloRequest> b2s(
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall(getB2sMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service HelloService.
   * <pre>
   * 定义服务
   * </pre>
   */
  public static abstract class HelloServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return HelloServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service HelloService.
   * <pre>
   * 定义服务
   * </pre>
   */
  public static final class HelloServiceStub
      extends io.grpc.stub.AbstractAsyncStub<HelloServiceStub> {
    private HelloServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单rpc,参数为HelloRequest类型，返回类型为HelloResponse 
     * </pre>
     */
    public void hello(com.levi.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 多值rpc,参数为ManyHelloRequest类型，返回类型为ManyHelloResponse 
     * </pre>
     */
    public void manyHello(com.levi.HelloProto.ManyHelloRequest request,
        io.grpc.stub.StreamObserver<com.levi.HelloProto.ManyHelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getManyHelloMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 服务端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    public void c2ss(com.levi.HelloProto.HelloRequest request,
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncServerStreamingCall(
          getChannel().newCall(getC2ssMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 客户端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloRequest> cs2s(
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncClientStreamingCall(
          getChannel().newCall(getCs2sMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 双向流式rpc,参数为HelloRequest类型，返回类型为HelloResponse,请求参数和响应参数都是stream表示请求和响应两端是双向流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloRequest> b2s(
        io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse> responseObserver) {
      return io.grpc.stub.ClientCalls.asyncBidiStreamingCall(
          getChannel().newCall(getB2sMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service HelloService.
   * <pre>
   * 定义服务
   * </pre>
   */
  public static final class HelloServiceBlockingV2Stub
      extends io.grpc.stub.AbstractBlockingStub<HelloServiceBlockingV2Stub> {
    private HelloServiceBlockingV2Stub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceBlockingV2Stub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceBlockingV2Stub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单rpc,参数为HelloRequest类型，返回类型为HelloResponse 
     * </pre>
     */
    public com.levi.HelloProto.HelloResponse hello(com.levi.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 多值rpc,参数为ManyHelloRequest类型，返回类型为ManyHelloResponse 
     * </pre>
     */
    public com.levi.HelloProto.ManyHelloResponse manyHello(com.levi.HelloProto.ManyHelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getManyHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 服务端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<?, com.levi.HelloProto.HelloResponse>
        c2ss(com.levi.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingV2ServerStreamingCall(
          getChannel(), getC2ssMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 客户端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse>
        cs2s() {
      return io.grpc.stub.ClientCalls.blockingClientStreamingCall(
          getChannel(), getCs2sMethod(), getCallOptions());
    }

    /**
     * <pre>
     * 双向流式rpc,参数为HelloRequest类型，返回类型为HelloResponse,请求参数和响应参数都是stream表示请求和响应两端是双向流
     * </pre>
     */
    @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/10918")
    public io.grpc.stub.BlockingClientCall<com.levi.HelloProto.HelloRequest, com.levi.HelloProto.HelloResponse>
        b2s() {
      return io.grpc.stub.ClientCalls.blockingBidiStreamingCall(
          getChannel(), getB2sMethod(), getCallOptions());
    }
  }

  /**
   * A stub to allow clients to do limited synchronous rpc calls to service HelloService.
   * <pre>
   * 定义服务
   * </pre>
   */
  public static final class HelloServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<HelloServiceBlockingStub> {
    private HelloServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单rpc,参数为HelloRequest类型，返回类型为HelloResponse 
     * </pre>
     */
    public com.levi.HelloProto.HelloResponse hello(com.levi.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 多值rpc,参数为ManyHelloRequest类型，返回类型为ManyHelloResponse 
     * </pre>
     */
    public com.levi.HelloProto.ManyHelloResponse manyHello(com.levi.HelloProto.ManyHelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getManyHelloMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 服务端流式rpc,参数为HelloRequest类型，返回类型为HelloResponse
     * </pre>
     */
    public java.util.Iterator<com.levi.HelloProto.HelloResponse> c2ss(
        com.levi.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.blockingServerStreamingCall(
          getChannel(), getC2ssMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service HelloService.
   * <pre>
   * 定义服务
   * </pre>
   */
  public static final class HelloServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<HelloServiceFutureStub> {
    private HelloServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected HelloServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new HelloServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单rpc,参数为HelloRequest类型，返回类型为HelloResponse 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.levi.HelloProto.HelloResponse> hello(
        com.levi.HelloProto.HelloRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getHelloMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * 多值rpc,参数为ManyHelloRequest类型，返回类型为ManyHelloResponse 
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.levi.HelloProto.ManyHelloResponse> manyHello(
        com.levi.HelloProto.ManyHelloRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getManyHelloMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_HELLO = 0;
  private static final int METHODID_MANY_HELLO = 1;
  private static final int METHODID_C2SS = 2;
  private static final int METHODID_CS2S = 3;
  private static final int METHODID_B2S = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_HELLO:
          serviceImpl.hello((com.levi.HelloProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse>) responseObserver);
          break;
        case METHODID_MANY_HELLO:
          serviceImpl.manyHello((com.levi.HelloProto.ManyHelloRequest) request,
              (io.grpc.stub.StreamObserver<com.levi.HelloProto.ManyHelloResponse>) responseObserver);
          break;
        case METHODID_C2SS:
          serviceImpl.c2ss((com.levi.HelloProto.HelloRequest) request,
              (io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CS2S:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.cs2s(
              (io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse>) responseObserver);
        case METHODID_B2S:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.b2s(
              (io.grpc.stub.StreamObserver<com.levi.HelloProto.HelloResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getHelloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.levi.HelloProto.HelloRequest,
              com.levi.HelloProto.HelloResponse>(
                service, METHODID_HELLO)))
        .addMethod(
          getManyHelloMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.levi.HelloProto.ManyHelloRequest,
              com.levi.HelloProto.ManyHelloResponse>(
                service, METHODID_MANY_HELLO)))
        .addMethod(
          getC2ssMethod(),
          io.grpc.stub.ServerCalls.asyncServerStreamingCall(
            new MethodHandlers<
              com.levi.HelloProto.HelloRequest,
              com.levi.HelloProto.HelloResponse>(
                service, METHODID_C2SS)))
        .addMethod(
          getCs2sMethod(),
          io.grpc.stub.ServerCalls.asyncClientStreamingCall(
            new MethodHandlers<
              com.levi.HelloProto.HelloRequest,
              com.levi.HelloProto.HelloResponse>(
                service, METHODID_CS2S)))
        .addMethod(
          getB2sMethod(),
          io.grpc.stub.ServerCalls.asyncBidiStreamingCall(
            new MethodHandlers<
              com.levi.HelloProto.HelloRequest,
              com.levi.HelloProto.HelloResponse>(
                service, METHODID_B2S)))
        .build();
  }

  private static abstract class HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    HelloServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.levi.HelloProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("HelloService");
    }
  }

  private static final class HelloServiceFileDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier {
    HelloServiceFileDescriptorSupplier() {}
  }

  private static final class HelloServiceMethodDescriptorSupplier
      extends HelloServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    HelloServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (HelloServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new HelloServiceFileDescriptorSupplier())
              .addMethod(getHelloMethod())
              .addMethod(getManyHelloMethod())
              .addMethod(getC2ssMethod())
              .addMethod(getCs2sMethod())
              .addMethod(getB2sMethod())
              .build();
        }
      }
    }
    return result;
  }
}
