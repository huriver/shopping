package com.ahut.service;

import com.ahut.pojo.User;

public interface UserService {

    // 用户登录方法
    User login(String username,String password);

    // 用户注册方法
    boolean register(User user);

    // 通过用户名查询用户方法
    boolean selectByUsername(String username);
}



