package com.zxy.service;

//dependencies
import com.zxy.repo.UserRepository;
import com.zxy.po.User;
import com.zxy.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService {

    //declare new user repository
    @Autowired
    private UserRepository userRepository;

    //retrieve user bu username and id
    @Override
    public User checkUser(String username, String password) {
        //use MD5Utils to encode password (for extra security)
        User user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
