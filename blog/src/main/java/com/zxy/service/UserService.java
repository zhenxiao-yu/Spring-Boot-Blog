package com.zxy.service;

import com.zxy.po.User;

public interface UserService {
    //check user username and password
    User checkUser(String username, String password);
}
