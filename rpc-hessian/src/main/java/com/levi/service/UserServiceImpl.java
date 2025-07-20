package com.levi.service;

import com.levi.domin.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserServiceImpl implements UserService {
    @Override
    public boolean login(String name, String password) {
        log.info("login method invoke: name:{}, password:{}", name, password);
        return false;
    }

    @Override
    public void register(User user) {
        log.info("register method invoke: {}", user);
    }
}
