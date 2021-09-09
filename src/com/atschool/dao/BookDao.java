package com.atschool.dao;

import com.atschool.pojo.Book;

import java.util.List;

public interface BookDao {

    int addBook(Book book);

    int deleteBookById(int id);

    int updateBook(Book book);

    Book queryBookById(int id);

    List<Book> queryBooks();

    int queryForPageTotalCount();

    List<Book> queryForItems(int begin, int pageSize);

    int queryForPageTotalCountByPrice(int min, int max);

    List<Book> queryForItemsByPrice(int begin, int pageSize, int min, int max);
}
