package com.atschool.service;

import com.atschool.dao.OrderDao;
import com.atschool.dao.impl.OrderDaoImpl;
import com.atschool.pojo.Cart;
import com.atschool.pojo.Order;
import com.atschool.pojo.Page;

public interface OrderService {

    String createOrder(Cart cart, int userId);

    Page<Order> page(int userId, int pageNo, int pageSize);
}
