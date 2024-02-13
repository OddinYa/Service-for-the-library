package org.course_work.DAO;

import org.course_work.entity.User;
import org.course_work.struct.map.MyMap;

public class UserDAOImpl implements UserDAO{
    MyMap map = new MyMap();

    @Override
    public void registerNewUser(User user) {
        map.put(user.getNumberOfTheTicket(),user);
    }

    @Override
    public void unregisterUser(String libraryCardNumber) {
        map.remove(libraryCardNumber);
    }

    @Override
    public MyMap getAllUsers() {
        return map;
    }

    @Override
    public void clearUserData() {
        map = null;
    }

    @Override
    public User findUserByLibraryCardNumber(String libraryCardNumber) {
        return map.get(libraryCardNumber);
    }

    @Override
    public User findUserByFullName(String fullName) {
        return map.findByName(fullName);
    }
}
