package com.levi.service;

import com.levi.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 注意这里实现的接口不是UserService，而是UserService.Iface，这是thrift生成的接口
 * 按照thrift的规范，每个接口都有一个对应的Iface接口，用于封装接口的方法，方便调用
 */
public class UserServiceImpl {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    public void registerUser(User user) {
        logger.info("register user:{}",user);
    }

    public User getUser(String name) {
        logger.info("get user:{}",name);
        return new User(name,"123456");
    }
}
