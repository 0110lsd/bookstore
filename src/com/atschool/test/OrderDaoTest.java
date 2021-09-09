package com.atschool.test;

import com.atschool.dao.OrderDao;
import com.atschool.dao.impl.OrderDaoImpl;
import com.atschool.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrderDaoTest {

    private OrderDao orderDao = new OrderDaoImpl();

    @Test
    public void saveOrder() {

        Order order = new Order("788479", new Date(), 0, new BigDecimal(89.90), 2);

        int i = orderDao.saveOrder(order);
        System.out.println(i);
    }

    @Test
    public void queryForPageTotalCount() {

        int pageTotalCount = orderDao.queryForPageTotalCount(1);

        System.out.println(pageTotalCount);
    }

    @Test
    public void queryForItems() {

        List<Order> orders = orderDao.queryForItems(1,1, 4);

        for (Order order : orders) {

            System.out.println(order);
        }
    }
}
