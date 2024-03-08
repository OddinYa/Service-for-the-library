package org.course_work.controller;

import org.course_work.DAO.BookDAOImpl;
import org.course_work.entity.Book;
import org.course_work.service.MergeSort;

import java.io.IOException;

public class BookController {

    BookDAOImpl bookDAO;

    public BookController() {
        bookDAO = new BookDAOImpl();
    }

    public Book getBook(String cipher) {
        Book book = bookDAO.findBookByCipher(cipher);

        if(book==null){
            return null;
        }
        if(book.getAvailableCopies()==0){
            return null;
        }

        return book;
    }

    public Book[] getAllBooks() {
        return bookDAO.getAllBooks();
    }

    public void closeWR() {
        bookDAO.close();
    }

    public void registrationBook(Book book) {
        bookDAO.addNewBook(book);
    }
    public String getInfoBook(Book book, int index){
        return index + ". Шифр книги :" + book.getCipher() + ",Название :" + book.getTitle() + ",Автор: " + book.getAuthor();
    }
    public void removeBook(String cipher){
        bookDAO.removeBook(cipher);
    }

    public Book[] findSortListBooks(String authorOrTitle){
        MergeSort<Book> sortedArr = new MergeSort<>();
        Book[] temp = bookDAO.findBooksByAuthorOrTitle(authorOrTitle);
        int count = 0;
        for (Book b: temp) {
            if(b!=null){
                count++;
            }
        }

        Book[] result = new Book[count];
        count = 0;
        for (Book b: temp) {
            if(b!=null){
               result[count] = b;
               count++;
            }
        }

        return sortedArr.sort(result);
    }
    public int getSerialNumber(int topicNumber){
        return bookDAO.getSerialNumber(topicNumber);
    }
    public void loadBook() throws IOException {
        bookDAO.loadBooks();
    }
}
