package org.course_work.DAO;

public interface DataDAO {
    void registerBookIssue(String libraryCardNumber, String cipher, String dateOfIssue); // регистрация выдачи экземпляра книги читателю
    void registerBookReturn(String libraryCardNumber, String cipher, String returnDate); // регистрация приема экземпляра книги от читателя
}
