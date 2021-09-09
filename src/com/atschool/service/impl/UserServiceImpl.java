package com.atschool.service.impl;

import com.atschool.dao.UserDao;
import com.atschool.dao.impl.UserDaoImpl;
import com.atschool.pojo.User;
import com.atschool.service.UserService;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    /**
     * 查询用户名是否已存在
     * @param username
     * @return
     */
    @Override
    public boolean existsUsername(String username) {

        User user = userDao.queryUserByUsername(username);

        if(user == null) {
            return false;
        }
        return true;
    }

    /**
     * 注册
     * @param user
     */
    @Override
    public void register(User user) {

        userDao.saveUser(user);

    }

    /**
     * 登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        User loginUser = userDao.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());

        return loginUser;
    }
}
