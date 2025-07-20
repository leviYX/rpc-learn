package com.levi.service;

import com.levi.User;
import com.levi.UserService;

/**
 * 注意这里实现的接口不是UserService，而是UserService.Iface，这是thrift生成的接口
 * 按照thrift的规范，每个接口都有一个对应的Iface接口，用于封装接口的方法，方便调用
 */
public class UserServiceImplRPC implements UserService.Iface{

    @Override
    public void registerUser(User user) {
        UserServiceImpl userService = new UserServiceImpl();
        userService.registerUser(user);
    }

    @Override
    public User getUser(String name) {
        UserServiceImpl userService = new UserServiceImpl();
        return userService.getUser(name);
    }
}
