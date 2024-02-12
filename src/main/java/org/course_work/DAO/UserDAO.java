package org.course_work.DAO;

import org.course_work.entity.User;
import org.course_work.struct.map.MyMap;

public interface UserDAO {
    void registerNewUser(User user); // регистрация нового читателя
    void unregisterUser(String libraryCardNumber); // снятие с обслуживания читателя
    MyMap getAllUsers(); // просмотр всех зарегистрированных читателей
    void clearUserData(); // очистка данных о читателях
    User findUserByLibraryCardNumber(String libraryCardNumber); // поиск читателя по номеру читательского билета
    User findUserByFullName(String fullName); // поиск читателя по ФИО
}
