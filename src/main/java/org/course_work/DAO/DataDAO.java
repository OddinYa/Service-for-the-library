package org.course_work.DAO;

public interface DataDAO {
    void registerBookIssue(String libraryCardNumber, String cipher ); // регистрация выдачи экземпляра книги читателю
    void registerBookReturn(String libraryCardNumber, String cipher); // регистрация приема экземпляра книги от читателя

    void removeCipher(String cipher);

    void removeTicket(String ticket);
}
