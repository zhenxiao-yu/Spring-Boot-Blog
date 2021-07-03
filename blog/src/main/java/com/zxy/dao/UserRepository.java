package com.zxy.dao;

import com.zxy.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    //look for user data in sql database
    User findByUsernameAndPassword(String username, String password);

}
