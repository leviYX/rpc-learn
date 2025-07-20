package com.levi.rpcClient;

import com.caucho.hessian.client.HessianProxyFactory;
import com.levi.service.UserService;
import lombok.extern.slf4j.Slf4j;

import java.net.MalformedURLException;

@Slf4j
public class HessianRpcClient {
    private static final String URL = "http://127.0.0.1:8080/rpc-hessian/userServiceRpc";

    public static void main(String[] args) throws MalformedURLException {
        HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
        UserService userService = (UserService) hessianProxyFactory.create(UserService.class, URL);
        boolean loginRes = userService.login("yxy", "123456");
        log.info("loginRes:{}", loginRes);
    }
}
