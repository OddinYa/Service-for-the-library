package org.course_work.controller;

import org.course_work.DAO.UserDAOImpl;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;

public class UserController {

    UserDAOImpl userDAO;

    public UserController(){
        userDAO = new UserDAOImpl();
    }

    public User registration(char accessRights, String fullName,
                             int yearOfBirth, String address, String placeOfWorkOrStudy) throws AccessRightsException {

        User user = new User(accessRights,fullName,yearOfBirth,address,placeOfWorkOrStudy);

        userDAO.registerNewUser(user);

        return user;

    }
    public String getCard(User user){
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ваш номер читательсого белета: "+user.getNumberOfTheTicket());

        return stringBuilder.toString();
    }

    public User find(String code) {
        return userDAO.findUserByLibraryCardNumber(code);
    }
}
