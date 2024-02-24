package org.course_work.view.console;

import org.course_work.controller.BookController;
import org.course_work.controller.DataController;
import org.course_work.controller.UserController;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleDisplay {

    BufferedReader reader;
    UserController userController;
    DataController dataController;
    BookController bookController;

    public ConsoleDisplay() {
        userController = new UserController();
        dataController = new DataController();
        bookController = new BookController();


        action();
    }

    private void action() {
        try {
            mainMenu();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (AccessRightsException e) {
            throw new RuntimeException(e);
        }
    }


    private void mainMenu() throws IOException, AccessRightsException {
        boolean flag = false;
        while (!flag) {
            System.out.println("Введите EXIT для выхода из меню!");
            System.out.println("Введите номер билета или \"0\" если еще не получили билет:  ");
            reader = new BufferedReader(new InputStreamReader(System.in));
            String code = reader.readLine();
            if(code.equals("EXIT")){
                flag = true;

            } else if (code.length() == 8) {
                User user = userController.find(code);
                System.out.println(user.toString());
            } else {
                if (code.equals("0")) {
                    System.out.println("Введите букву Абонимента:");
                    String A = reader.readLine();
                    System.out.println("Введите Полное имя:");
                    String name = reader.readLine();
                    System.out.println("Введите кол-во полных лет:");
                    String age = reader.readLine();
                    System.out.println("Введите адрес проживания:");
                    String address = reader.readLine();
                    System.out.println("Введите место работы/учебы");
                    String placeOfWorkOrStudy = reader.readLine();
                    User user = userController.registration(A.charAt(0), name, Integer.parseInt(age), address, placeOfWorkOrStudy);
                    System.out.println(userController.getCard(user));
                    action();
                } else {
                    System.out.println("Ошибка номера");

                }
            }
        }

    }
}
