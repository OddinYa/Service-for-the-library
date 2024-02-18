package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.struct.tree.TreeSet;

public interface BookDAO {
    void addNewBook(Book book);
    void removeBook(String cipher);
    TreeSet getAllBooks();
    void clearBookData();
    Book findBookByCipher(String cipher);
    Book findBooksByAuthorOrTitle(String searchQuery); // поиск книги по фрагментам ФИО автора(ов) или названия
}

