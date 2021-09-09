package com.atschool.dao.impl;

import com.atschool.dao.BaseDao;
import com.atschool.dao.BookDao;
import com.atschool.pojo.Book;

import java.util.List;

public class BookDaoImpl extends BaseDao implements BookDao {


    @Override
    public int addBook(Book book) {

        String sql = "insert into t_book(name, author, price, sales, stock, img_path) values" +
                "(?,?,?,?,?,?)";
        int update = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());

        return update;
    }

    @Override
    public int deleteBookById(int id) {
        String sql = "delete from t_book where id = ?";
        int update = update(sql, id);

        return update;
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update t_book set name = ?, author = ?, price = ?, sales = ?, stock = ?, img_path = ? where id = ?";
        int update = update(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
        return update;
    }

    @Override
    public Book queryBookById(int id) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book where id = ?";
        Book book = queryForOne(Book.class, sql, id);
        return book;
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book";
        List<Book> books = queryForList(Book.class, sql);
        return books;
    }

    @Override
    public int queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        Number count = (Number)queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItems(int begin, int pageSize) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath " +
                "from t_book limit ?, ?";
        List<Book> books = queryForList(Book.class, sql, begin, pageSize);
        return books;
    }

    @Override
    public int queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        Number count = (Number)queryForSingleValue(sql, min, max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql = "select id, name, author, price, sales, stock, img_path imgPath from t_book" +
                " where price between ? and ? order by price limit ?, ?";
        List<Book> books = queryForList(Book.class, sql, min, max, begin, pageSize);
        return books;
    }
}
