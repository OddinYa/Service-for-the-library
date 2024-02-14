package org.course_work.controller;

import org.course_work.DAO.BookDAOImpl;
import org.course_work.entity.Book;

public class BookController {

    BookDAOImpl bookDAO;


    public BookController(){
        bookDAO = new BookDAOImpl();
    }
    public Book getBook(String chipher){
      return bookDAO.findBookByCipher(chipher);
    }
    
}
