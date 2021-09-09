package com.atschool.service.impl;

import com.atschool.dao.BookDao;
import com.atschool.dao.OrderDao;
import com.atschool.dao.OrderItemDao;
import com.atschool.dao.impl.BookDaoImpl;
import com.atschool.dao.impl.OrderDaoImpl;
import com.atschool.dao.impl.OrderItemDaoImpl;
import com.atschool.pojo.*;
import com.atschool.service.OrderService;

import java.util.Date;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    /**
     * 创建订单
     * @param cart
     * @param userId
     * @return
     */
    @Override
    public String createOrder(Cart cart, int userId) {

        String orderId = System.currentTimeMillis() + userId + "";

        Order order = new Order(orderId, new Date(), 0, cart.getTotalPrice(), userId);

        orderDao.saveOrder(order);

        for(int key : cart.getItems().keySet()) {

            //保存订单项
            CartItem cartItem = cart.getItems().get(key);
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getPrice(), cartItem.getCount(), cartItem.getTotalPrice(), orderId);
            orderItemDao.saveOrderItem(orderItem);

            //更新图书销量和库存
            Book book = bookDao.queryBookById(key);
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());
            bookDao.updateBook(book);
        }

        //清空购物车
        cart.clear();

        return orderId;
    }

    /**
     * 订单分页
     * @param userId
     * @param pageNo
     * @param pageSize
     * @return
     */
    @Override
    public Page<Order> page(int userId, int pageNo, int pageSize) {

        Page<Order> page = new Page<>();

        int pageTotalCount = orderDao.queryForPageTotalCount(userId);

        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0) {
            pageTotal += 1;
        }

        int begin = (pageNo - 1) * pageSize;
        List<Order> orders = orderDao.queryForItems(userId, begin, pageSize);

        page.setItems(orders);
        page.setPageSize(pageSize);
        page.setPageTotalCount(pageTotalCount);
        page.setPageTotal(pageTotal);
        page.setPageNo(pageNo);

        return page;
    }
}
