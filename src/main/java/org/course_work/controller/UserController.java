package org.course_work.controller;

import org.course_work.DAO.UserDAOImpl;
import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.service.MergeSort;

import java.io.IOException;

public class UserController {

    private UserDAOImpl userDAO;

    public UserController() {
        userDAO = new UserDAOImpl();
    }

    public User registration(char accessRights, String fullName,
                             int yearOfBirth, String address, String placeOfWorkOrStudy) throws AccessRightsException {

        User user = new User(accessRights, fullName, yearOfBirth, address, placeOfWorkOrStudy);

        userDAO.registerNewUser(user);

        return user;

    }
    public User find(String code) {
        return userDAO.findUserByLibraryCardNumber(code);
    }



    public void loadUser() throws IOException {
        userDAO.loadUser();
    }

    public String getInfo(User user, DataController data, BookController bookController) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Номер билета: " + user.getNumberOfTheTicket() + " ,ФИО: " + user.getFullName()+"\n");
        stringBuilder.append("Книги на руках: "+"\n");

        DataOnTheIssuanceAndAcceptanceOfBooks[] findData = data.getList(user.getNumberOfTheTicket());
        int count = 1;
        for (DataOnTheIssuanceAndAcceptanceOfBooks d : findData) {
            if (d.getReturnDate() == null) {
                Book book = bookController.getBook(d.getCipher());
                stringBuilder.append(count + ". Шифр: " + book.getCipher() + ",Название: " + book.getTitle() + ",Автор: " + book.getAuthor()+"\n");
                count++;
            }

        }

        return stringBuilder.toString();
    }
    public String getInfoShort(User user,int index) {

        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(index +". Номер билета: " + user.getNumberOfTheTicket() + ",ФИО "+ user.getFullName());

        return stringBuilder.toString();
    }
    public String getInfoShort(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Номер билета: " + user.getNumberOfTheTicket() + ",ФИО "+ user.getFullName());

        return stringBuilder.toString();
    }

    public void removeUser(String ticket){
        userDAO.remove(ticket);
    }

    public User[] getSortAllUsers(){
        MergeSort<User> userSorted = new MergeSort<>();

        User[] users = userDAO.getAllUsers().toArray();
        User[] result =(User[])userSorted.sort(users);  // TODO Ошибка

        return result;
    }
    public User[] getListFindFullName(String fullName){
        MergeSort<User> userSorted = new MergeSort<>();

        User[] result = userDAO.findUserByFullName(fullName).toArray();

        return userSorted.sort(result);

    }
}
