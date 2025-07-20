package com.levi;

import com.levi.service.UserServiceImpl;
import com.levi.service.UserServiceImplRPC;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;

public class TestServer {
    public static void main(String[] args) {
        // 创建服务端，监听端口8888，其实就是对应的TTransport组件，通信功能在此封装
        try (TServerSocket tServerSocket = new TServerSocket(8888);){
            // 协议工厂，用于封装协议，比如二进制协议、JSON协议等，此处使用二进制协议，其实对应的就是TProtocol组件，协议功能在此封装
            TBinaryProtocol.Factory protocolFactory = new TBinaryProtocol.Factory();
            // 处理器工厂，用于封装处理器，比如UserService.Processor，其实对应的就是TProcessor组件，处理器功能在此封装
            UserService.Processor processor = new UserService.Processor(new UserServiceImplRPC());
            // 封装参数
            TSimpleServer.Args arg = new TSimpleServer.Args(tServerSocket).protocolFactory(protocolFactory).processor(processor);
            // 构建服务端起动器，其实对应的就是TServer组件，服务端功能在此封装，发布服务出去，等待客户端连接
            TServer server = new TSimpleServer(arg);
            server.serve();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
