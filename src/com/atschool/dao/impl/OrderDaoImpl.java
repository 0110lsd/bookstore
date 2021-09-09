package com.atschool.dao.impl;

import com.atschool.dao.BaseDao;
import com.atschool.dao.OrderDao;
import com.atschool.pojo.Order;

import java.util.List;

public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id, create_time, status, price, user_id) values" +
                "(?, ?, ?, ?, ?)";

        int update = update(sql, order.getOrderId(), order.getCreateTime(), order.getStatus(), order.getPrice(), order.getUserId());
        return update;
    }

    @Override
    public int queryForPageTotalCount(int userId) {
        String sql = "select count(*) from t_order where user_id = ?";

        Number count = (Number)queryForSingleValue(sql, userId);

        return count.intValue();
    }

    @Override
    public List<Order> queryForItems(int userId, int begin, int pageSize) {
        String sql = "select order_id orderId, create_time createTime, status, price, user_id userId from t_order where user_id = ? order by create_time limit ?, ?";

        List<Order> orders = queryForList(Order.class, sql, userId, begin, pageSize);

        return orders;
    }
}
