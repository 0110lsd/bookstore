package com.atschool.test;

import com.atschool.dao.BookDao;
import com.atschool.dao.impl.BookDaoImpl;
import com.atschool.pojo.Book;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTest {

    private BookDao bookDao = new BookDaoImpl();

    @Test
    public void addBook() {

        Book book = new Book(null, "说话的艺术", "路锁东", new BigDecimal(18.75), 467557, 3675, "/static/img/def.jpeg");

        int i = bookDao.addBook(book);
        System.out.println(i);
    }

    @Test
    public void deleteBookById() {

        int i = bookDao.deleteBookById(9);
        System.out.println(i);
    }

    @Test
    public void updateBook() {

        Book book = new Book(4, "酸菜肉丝面", "5斤", new BigDecimal(24.73), 88937, 3435, "/static/img/def.jpeg");

        int i = bookDao.updateBook(book);

        System.out.println(i);
    }

    @Test
    public void queryBookById() {

        Book book = bookDao.queryBookById(1);
        System.out.println(book);
    }

    @Test
    public void queryBooks() {
        List<Book> books = bookDao.queryBooks();
        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryTotalCount() {

        int pageTotalCount = bookDao.queryForPageTotalCount();

        System.out.println(pageTotalCount);
    }

    @Test
    public void queryForItems() {
        List<Book> books = bookDao.queryForItems(1, 4);

        for (Book book : books) {
            System.out.println(book);
        }
    }

    @Test
    public void queryForTotalCountByPrice() {

        int i = bookDao.queryForPageTotalCountByPrice(10, 59);
        System.out.println(i);
    }

    @Test
    public void queryForItemsByPrice() {
        List<Book> books = bookDao.queryForItemsByPrice(1, 4, 10, 100);

        for (Book book : books) {
            System.out.println(book);
        }
    }
}