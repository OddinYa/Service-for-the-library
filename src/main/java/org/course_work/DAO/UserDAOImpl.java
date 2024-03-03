package org.course_work.DAO;

import org.course_work.entity.User;
import org.course_work.service.UserFile;
import org.course_work.struct.map.MyMap;

import java.io.IOException;

public class UserDAOImpl implements UserDAO{
    private MyMap map;
    private UserFile userFile;


    public UserDAOImpl(){
        userFile = new UserFile();
        map = userFile.readerFile();


    }
    public MyMap getMap(){
        return map;
    }

    @Override
    public void registerNewUser(User user) {
        map.put(user.getNumberOfTheTicket(),user);
        userFile.writeToFile(user);

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
    public MyMap findUserByFullName(String fullName) {
        return map.findByName(fullName);
    }

    @Override
    public void remove(String ticket) {

    }

    public void load() throws IOException {
        User[] arr = map.toArray();
        userFile.load(arr);

    }



    public void closeStream(){
        userFile.closeFile();
    }
}
