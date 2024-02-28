package org.course_work.controller;

import org.course_work.DAO.BookDAOImpl;
import org.course_work.entity.Book;

public class BookController {

    BookDAOImpl bookDAO;

    public BookController() {
        bookDAO = new BookDAOImpl();
    }

    public Book getBook(String cipher) {
        return bookDAO.findBookByCipher(cipher);
    }

    public Book[] getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public void closeWR() {
        bookDAO.close();
    }

    public void registrationBook(Book book) {

    }
}
