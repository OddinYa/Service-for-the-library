package org.course_work.view.console;

import org.course_work.controller.BookController;
import org.course_work.controller.DataController;
import org.course_work.controller.UserController;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.service.PasswordHasher;
import org.course_work.struct.tree.Tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class ConsoleDisplay extends Thread {

    private BufferedReader reader;
    private UserController userController;
    private DataController dataController;
    private BookController bookController;

    private PasswordHasher passwordHasher;

    public ConsoleDisplay() {

       this.start();


        action();
    }

    private void action() {
        try {
            mainMenu();
        } catch (IOException  | AccessRightsException e) {
           e.getStackTrace();
        }

    }


    private void mainMenu() throws IOException, AccessRightsException {
        boolean flag = false;

        while (!flag) {
            try {


                System.out.println("Введите EXIT для выхода из меню!");
                System.out.println("Введите номер билета или \"0\" если еще не получили билет:  ");
                reader = new BufferedReader(new InputStreamReader(System.in));
                String code = reader.readLine();
                if (code.equals("EXIT")) {
                    flag = true;
                    userController.closeWR();

                } else if (code.length() == 8) {
                    if (code.equals("А0001-24")) {
                        adminMenu();
                    } else {
                        User user = userController.find(code);

                        if (user != null) {
                            userMenu(user);
                        }
                    }
                    System.out.println("Пользователь не найден или ошибка ввода!");
                } else {
                    if (code.equals("0")) {
                        System.out.println("Введите букву Абонимента:");
                        String A = reader.readLine();

                        System.out.println("Введите Полное имя:");
                        String name = reader.readLine();

                        System.out.println("Введите кол-во полных лет:");
                        int age = Integer.parseInt(reader.readLine());

                        System.out.println("Введите адрес проживания:");
                        String address = reader.readLine();

                        System.out.println("Введите место работы/учебы");
                        String placeOfWorkOrStudy = reader.readLine();

                        User user = userController.registration(A.charAt(0), name, age, address, placeOfWorkOrStudy);
                        System.out.println(userController.getCard(user));

                    } else {
                        System.out.println("Ошибка номера");

                    }
                }
            } catch (Exception e) {
                System.out.println("Ошибка ввода!");
            }

        }
    }

    private void userMenu(User user) {
        //TODO
        //1. Сдать книгу
        //2. Взять книгу
        //3. Книги на руках
    }

    private void adminMenu()throws IOException {
        passwordHasher = new PasswordHasher();
        System.out.println("Ввидете пароль админа:");
        String code = reader.readLine();
        if(passwordHasher.verifyPassword(code)){

        }else{
            System.out.println("ОШИБКА ПАРОЛЯ!");
        }


        //TODO
        //Все процедуры с книгами
        //Поиски пользователь

    }

    @Override
    public void run() {
        userController = new UserController();
        dataController = new DataController();
        bookController = new BookController();

    }
}
