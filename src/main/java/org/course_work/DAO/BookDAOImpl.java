package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.struct.tree.TreeSet;

public class BookDAOImpl implements BookDAO{
    TreeSet treeSet;
    public BookDAOImpl(){
        treeSet = new TreeSet();
    }

    @Override
    public void addNewBook(Book book) {
        treeSet.insert(book);
    }

    @Override
    public void removeBook(String cipher) {
        treeSet.delete(findBookByCipher(cipher));
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
