package com.levi.service;

import com.levi.domin.User;

public interface UserService {
    public boolean login(String name, String password);
    public void register(User user);
}
