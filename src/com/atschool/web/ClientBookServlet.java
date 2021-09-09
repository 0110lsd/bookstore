package com.atschool.web;

import com.atschool.pojo.Book;
import com.atschool.pojo.Page;
import com.atschool.service.BookService;
import com.atschool.service.impl.BookServiceImpl;
import com.atschool.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ClientBookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 查询图书分页信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNo = req.getParameter("pageNo");

        int pageNum = WebUtils.parseInt(pageNo, 1);

        Page<Book> page = bookService.page(pageNum, Page.PAGE_SIZE);

        page.setUrl("client/bookServlet?action=page");

        req.setAttribute("page", page);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    /**
     * 根据价格查询图书分页信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);

        Page<Book> bookPage = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder sb = new StringBuilder("client/bookServlet?action=pageByPrice");

        if(req.getParameter("min") != null) {
            sb.append("&min=").append(req.getParameter("min"));
        }
        if(req.getParameter("max") != null) {
            sb.append("&max=").append(req.getParameter("max"));
        }

        bookPage.setUrl(sb.toString());

        req.setAttribute("page", bookPage);

        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }
}
