package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.struct.tree.TreeSet;

public interface BookDAO {
    void addNewBook(Book book); // добавление новой книги
    void removeBook(String cipher); // удаление сведений о книге
    TreeSet getAllBooks(); // просмотр всех имеющихся книг
    void clearBookData(); // очистка данных о книгах
    Book findBookByCipher(String cipher); // поиск книги по шифру
    Book findBooksByAuthorOrTitle(String searchQuery); // поиск книги по фрагментам ФИО автора(ов) или названия
}

