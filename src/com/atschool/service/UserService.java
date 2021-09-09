package com.atschool.service;

import com.atschool.pojo.User;

public interface UserService {

    boolean existsUsername(String username);

    void register(User user);

    User login(User user);
}
