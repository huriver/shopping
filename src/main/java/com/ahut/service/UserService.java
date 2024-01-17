package com.ahut.service;

import com.ahut.pojo.User;

public interface UserService {


    User login(String username,String password);

    boolean register(User user);

    boolean selectByUsername(String username);
}



