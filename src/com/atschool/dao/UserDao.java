package com.atschool.dao;

import com.atschool.pojo.User;

public interface UserDao {

    int saveUser(User user);

    User queryUserByUsername(String username);

    User queryUserByUsernameAndPassword(String username, String password);

}
