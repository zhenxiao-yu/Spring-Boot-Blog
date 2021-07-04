package com.zxy.service;

import com.zxy.dao.UserRepository;
import com.zxy.po.User;
//import com.zxy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Dependencies
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    //check user method
    @Override
    public User checkUser(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        //User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }

}
