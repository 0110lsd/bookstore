package com.atschool.dao;

import com.atschool.pojo.Order;

import java.util.List;

public interface OrderDao {

    int saveOrder(Order order);

    int queryForPageTotalCount(int userId);

    List<Order> queryForItems(int userId, int begin, int pageSize);
}
