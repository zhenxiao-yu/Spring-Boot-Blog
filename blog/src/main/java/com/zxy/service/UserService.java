package com.zxy.service;
//dependencies

import com.zxy.po.User;


public interface UserService {
    //retrieve user bu username and id
    User checkUser(String username, String password);
}
