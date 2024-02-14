package org.course_work.view.console;

import org.course_work.controller.BookController;
import org.course_work.controller.DataController;
import org.course_work.controller.UserController;


public class ConsoleDisplay {
    UserController userController;
    DataController dataController;
    BookController bookController;

    public ConsoleDisplay(){
        userController = new UserController();
        dataController = new DataController();
        bookController = new BookController();

        action();
    }

    private void action(){

    }


    private void mainMenu(){

    }
}
