package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.struct.tree.TreeSet;

public class BookDAOImpl implements BookDAO{

    public BookDAOImpl(){
        TreeSet treeSet = new TreeSet();
    }

    @Override
    public void addNewBook(Book book) {

    }

    @Override
    public void removeBook(String cipher) {

    }

    @Override
    public TreeSet getAllBooks() {
        return null;
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
}
