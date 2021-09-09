package com.atschool.dao.impl;

import com.atschool.dao.BaseDao;
import com.atschool.dao.OrderItemDao;
import com.atschool.pojo.OrderItem;

public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {


    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(id, name, count, price, total_price, order_id) values" +
                "(?,?,?,?,?,?)";
        int update = update(sql, orderItem.getId(), orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());
        return update;
    }
}
