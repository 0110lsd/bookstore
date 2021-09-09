package com.atschool.test;

import com.atschool.dao.UserDao;
import com.atschool.dao.impl.UserDaoImpl;
import com.atschool.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserDaoTest {

    private UserDao userDao = new UserDaoImpl();

    @Test
    public void saveUser() {

        User user = new User(null, "GaoQiang", "19980110", "highman@qq.com");

        int i = userDao.saveUser(user);

        System.out.println(i);
    }

    @Test
    public void queryUserByUsername() {

        User gaoQiang = userDao.queryUserByUsername("GaoQiang");

        System.out.println(gaoQiang);
    }

    @Test
    public void queryUserByUsernameAndPassword() {

        User gaoQiang = userDao.queryUserByUsernameAndPassword("GaoQiang", "19980110");

        System.out.println(gaoQiang);
    }
}