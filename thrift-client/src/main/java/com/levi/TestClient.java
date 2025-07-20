package com.levi;

import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestClient {
    private static Logger logger = LoggerFactory.getLogger(TestClient.class);

    public static void main(String[] args) {
        // 创建服务端，监听端口8888，其实就是对应的TTransport组件，通信功能在此封装
        try (TTransport tTransport = new TSocket("localhost", 8888);){
            // 协议工厂，用于封装协议，比如二进制协议、JSON协议等，此处使用二进制协议，其实对应的就是TProtocol组件，协议功能在此封装，和服务端保持一致
            TProtocol tProtocol = new TBinaryProtocol(tTransport);
            // rpc的本质，就是像调用本地服务一样调用远程服务，此处就是一个体现，但是本质都是代理模式，只不过这个代理thrift封装了
            UserService.Client userService = new UserService.Client(tProtocol);
            tTransport.open();
            User user = userService.getUser("levi");
            logger.info("client get user is {}",user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
