package com.atschool.test;

import com.atschool.dao.OrderItemDao;
import com.atschool.dao.impl.OrderItemDaoImpl;
import com.atschool.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemDaoTest {

    private OrderItemDao orderItemDao = new OrderItemDaoImpl();

    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null, "好兵帅克", new BigDecimal(46.95), 2, new BigDecimal(93.9), "16139596972631");
        int i = orderItemDao.saveOrderItem(orderItem);
        System.out.println(i);
    }
}
