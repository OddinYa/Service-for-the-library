package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.service.BookFile;
import org.course_work.service.MergeSort;
import org.course_work.struct.tree.Tree;

public class BookDAOImpl implements BookDAO{
    private Tree tree;

    private BookFile fileBook;

    public BookDAOImpl(){

        fileBook = new BookFile();
        tree = fileBook.readerFile();

    }

    @Override
    public void addNewBook(Book book) {

        try {
            tree.add(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeBook(String cipher) {
       // tree.delete(findBookByCipher(cipher));
    }

    @Override
    public Book[] getAllBooks() {

        Book[] books = tree.traversal();

    }

    @Override
    public void clearBookData() {

    }

    @Override
    public Book findBookByCipher(String cipher) {
        return null;
    }

    @Override
    public Book findBooksByAuthorOrTitle(String searchQuery) {
        return null;
    }

    public void close(){
        fileBook.closeFile();
    }
}
