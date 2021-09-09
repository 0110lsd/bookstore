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
import java.util.List;

public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 获取所有图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Book> books = bookService.queryBooks();

        req.setAttribute("books", books);

        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    /**
     * 添加图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNo = req.getParameter("pageNo");

        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        bookService.addBook(book);

        // 重定向回当前页
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 更新图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNo = req.getParameter("pageNo");

        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());

        bookService.updateBook(book);

        // 重定向回当前页
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 根据id删除图书
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pageNo = req.getParameter("pageNo");

        String bookId = req.getParameter("id");

        int id = WebUtils.parseInt(bookId, 0);

        bookService.deleteBookById(id);

        // 重定向回当前页
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);
    }

    /**
     * 根据id查询图书信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String bookId = req.getParameter("id");

        int id = WebUtils.parseInt(bookId, 0);

        Book book = bookService.queryBookById(id);

        req.setAttribute("book", book);

        //请求转发到图书编辑页
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);

    }

    /**
     * 查询图书分页数据
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String no = req.getParameter("pageNo");

        int pageNo = WebUtils.parseInt(no, 1);

        Page<Book> page = bookService.page(pageNo, Page.PAGE_SIZE);

        page.setUrl("manager/bookServlet?action=page");

        req.setAttribute("page", page);

        // 请求转发到图书管理页
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
