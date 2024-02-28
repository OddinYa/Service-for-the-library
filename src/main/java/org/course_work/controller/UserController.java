package org.course_work.controller;

import org.course_work.DAO.UserDAOImpl;
import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;

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

    public String getCard(User user) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Ваш номер читательсого белета: " + user.getNumberOfTheTicket());

        return stringBuilder.toString();
    }

    public User find(String code) {
        return userDAO.findUserByLibraryCardNumber(code);
    }

    public void closeWR() {
        userDAO.closeStream();
    }

    public String getInfo(User user, DataController data, BookController bookController) {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("Номер билета: " + user.getNumberOfTheTicket() + ",ФИО");
        stringBuilder.append("Книги на руках: ");

        DataOnTheIssuanceAndAcceptanceOfBooks[] findData = data.getList(user.getNumberOfTheTicket());
        int count = 1;
        for (DataOnTheIssuanceAndAcceptanceOfBooks d : findData) {
            if (d.getReturnDate() == null) {
                Book book = bookController.getBook(d.getCipher());
                stringBuilder.append(count + ". Шифр: " + book.getCipher() + ",Название: " + book.getTitle() + ",Автор: " + book.getAuthor());
                count++;
            }

        }

        return stringBuilder.toString();
    }
}
