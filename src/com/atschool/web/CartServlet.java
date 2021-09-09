package com.atschool.web;

import com.atschool.pojo.Book;
import com.atschool.pojo.Cart;
import com.atschool.pojo.CartItem;
import com.atschool.service.BookService;
import com.atschool.service.impl.BookServiceImpl;
import com.atschool.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 添加商品到购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Book book = bookService.queryBookById(id);

        CartItem cartItem = new CartItem(id, book.getName(), 1, book.getPrice(), book.getPrice());

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        if(cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }

        cart.addItem(cartItem);
        req.getSession().setAttribute("lastName", cartItem.getName());
        // 重定向回原来页面
        resp.sendRedirect(req.getHeader("Referer"));
    }

    /**
     * 删除购物车中商品
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        if(cart != null) {
            cart.deleteItem(id);

            resp.sendRedirect(req.getHeader("Referer"));
        }

    }

    /**
     * 修改购物车中商品数量
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updateCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        int id = WebUtils.parseInt(req.getParameter("id"), 1);

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        if(cart != null) {

            cart.updateCount(id, count);

            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clear(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Cart cart = (Cart)req.getSession().getAttribute("cart");

        if(cart != null) {

            cart.clear();

            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
