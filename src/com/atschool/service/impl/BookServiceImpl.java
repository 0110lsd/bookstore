package com.atschool.service.impl;

import com.atschool.dao.BookDao;
import com.atschool.dao.impl.BookDaoImpl;
import com.atschool.pojo.Book;
import com.atschool.pojo.Page;
import com.atschool.service.BookService;

import java.util.List;

/**
 * 图书管理
 */
public class BookServiceImpl implements BookService {

    private BookDao bookDao = new BookDaoImpl();

    /**
     * 添加图书
     * @param book
     */
    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    /**
     * 根据图书id删除图书
     * @param id
     */
    @Override
    public void deleteBookById(int id) {
        bookDao.deleteBookById(id);
    }

    /**
     * 修改图书信息
     * @param book
     */
    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    /**
     * 根据id查询图书
     * @param id
     * @return
     */
    @Override
    public Book queryBookById(int id) {
        Book book = bookDao.queryBookById(id);
        return book;
    }

    /**
     * 查询所有图书
     * @return
     */
    @Override
    public List<Book> queryBooks() {
        List<Book> books = bookDao.queryBooks();
        return books;
    }

    /**
     * 图书分页数据
     * @param pageNo 当前页
     * @param pageSize 每页记录数
     * @return
     */
    @Override
    public Page<Book> page(int pageNo, int pageSize) {

        Page<Book> page = new Page<>();

        int pageTotalCount = bookDao.queryForPageTotalCount();

        int pageTotal = pageTotalCount / pageSize;

        if(pageTotalCount % pageSize != 0) {
            pageTotal += 1;
        }

        int begin = (pageNo - 1) * pageSize;

        List<Book> books = bookDao.queryForItems(begin, pageSize);

        page.setItems(books);
        page.setPageSize(pageSize);
        page.setPageTotal(pageTotal);
        page.setPageTotalCount(pageTotalCount);
        page.setPageNo(pageNo);

        return page;
    }

    /**
     * 根据价格查询图书分页信息
     * @param pageNo 当前页
     * @param pageSize 每页记录数
     * @param min 最低价格
     * @param max 最高价格
     * @return
     */
    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {

        Page<Book> page = new Page<>();

        int pageTotalCount = bookDao.queryForPageTotalCountByPrice(min, max);
        int pageTotal = pageTotalCount / pageSize;
        if(pageTotalCount % pageSize != 0) {
            pageTotal += 1;
        }
        int begin = (pageNo - 1) * pageSize;
        List<Book> books = bookDao.queryForItemsByPrice(begin, pageSize, min, max);

        page.setPageTotal(pageTotal);
        page.setPageTotalCount(pageTotalCount);
        page.setPageSize(pageSize);
        page.setItems(books);
        page.setPageNo(pageNo);

        return page;
    }


}
