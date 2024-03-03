package org.course_work.controller;

import org.course_work.DAO.BookDAOImpl;
import org.course_work.entity.Book;
import org.course_work.service.MergeSort;

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

        return sortedArr.sort(temp);
    }
    public int getSerialNumber(int topicNumber){
        return bookDAO.getSerialNumber(topicNumber);
    }
}
