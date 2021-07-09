package com.zxy.service;
//dependencies

import com.zxy.entity.User;


public interface UserService {
    //retrieve user bu username and id
    User checkUser(String username, String password);
}
