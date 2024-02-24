package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.struct.tree.Tree;

public class BookDAOImpl implements BookDAO{
    Tree tree;
    public BookDAOImpl(){
        tree = new Tree();
    }

    @Override
    public void addNewBook(Book book) {

        try {
            tree.insert(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeBook(String cipher) {
        tree.delete(findBookByCipher(cipher));
    }

    @Override
    public Tree getAllBooks() {
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
