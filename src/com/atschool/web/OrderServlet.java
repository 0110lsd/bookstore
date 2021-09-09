package com.atschool.web;

import com.atschool.pojo.Cart;
import com.atschool.pojo.Order;
import com.atschool.pojo.Page;
import com.atschool.pojo.User;
import com.atschool.service.OrderService;
import com.atschool.service.impl.OrderServiceImpl;
import com.atschool.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderServlet extends BaseServlet {

    private OrderService orderService = new OrderServiceImpl();

    /**
     * 创建订单
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void createOrder(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        User user = (User)req.getSession().getAttribute("user");

        if(user == null) {  // 用户未登录

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = user.getId();

        String orderId = orderService.createOrder(cart, userId);

        req.getSession().setAttribute("orderId", orderId);

        resp.sendRedirect(req.getContextPath() + "/pages/cart/checkout.jsp");

    }

    /**
     * 查询商品项带分页
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        User user = (User)req.getSession().getAttribute("user");

        if(user == null) {

            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);
            return;
        }

        Integer userId = user.getId();

        Page<Order> orderPage = orderService.page(userId, pageNo, pageSize);

        orderPage.setUrl("orderServlet?action=page");

        req.setAttribute("page", orderPage);

        req.getRequestDispatcher("/pages/order/order.jsp").forward(req, resp);
    }
}
