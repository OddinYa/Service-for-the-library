package org.course_work.view.console;

import org.course_work.controller.BookController;
import org.course_work.controller.DataController;
import org.course_work.controller.UserController;
import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.exception.BookTopicNumberException;
import org.course_work.service.PasswordHasher;

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
        } catch (IOException | AccessRightsException e) {
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
                    close();
                    System.exit(0);

                } else if (code.length() == 8) {
                    if (code.equals("А0001-24")) {
                        adminMenu();

                    } else {
                        User user = userController.find(code);

                        if (user != null) {
                            System.out.println("Добро пожаловать!");
                            System.out.println(userController.getInfo(user, dataController, bookController));
                            userMenu(user);
                            close();
                            System.exit(0);
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
                        System.out.println(userController.getInfoShort(user));

                    } else {
                        System.out.println("Ошибка номера");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
                System.out.println("Ошибка ввода!");
            }

        }
    }

    private void userMenu(User user) throws IOException {
        boolean flag = false;
        while (!flag) {
            System.out.println("Меню:");
            System.out.println("1. Вернуть книгу \n" +
                    "2. Получить список книг \n" +
                    "3. Список книг на руках \n" +
                    "4. Найти книгу по автору или названию\n" +
                    "5. Найти книгу по коду\n" +
                    "6. Взять книгу по коду\n" +
                    "7. Выход.");

            String code = reader.readLine();

            switch (code) {
                case "1":
                    System.out.println("Введите шифр книги:");
                    String cipher = reader.readLine();
                    Book book = null;
                    try {
                        book = bookController.getBook(cipher);

                        if (book == null) {
                            System.out.println("Книга не найдена!");
                        } else {
                            DataOnTheIssuanceAndAcceptanceOfBooks data = dataController.
                                    getData(user.getNumberOfTheTicket(), book.getCipher());
                            if (data != null) {
                                data.setReturnDate();
                                book.setAvailableCopies(book.getAvailableCopies() + 1);

                                System.out.println("Книга возвращена.Спасибо!");
                            } else {
                                System.out.println("Данных по этой книги нет!");
                            }
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Не стандартный шифр, либо ошибка шифра!");
                    }
                    break;
                case "2":
                    Book[] allBook = bookController.getAllBooks();
                    System.out.println("Список книг: ");
                    int countBa = 1;
                    for (Book b : allBook) {
                        if (b.getAvailableCopies() > 0) {
                            System.out.println(bookController.getInfoBook(b, countBa));
                            countBa++;
                        }
                    }
                    System.out.println("Напишите шифр книги которую хотите взять или напишите EXIT");
                    String codeOfBook = reader.readLine();
                    if (codeOfBook.equals("EXIT")) {
                        break;
                    } else {
                        Book bookToGet = bookController.getBook(codeOfBook);

                        if (bookToGet == null) {
                            System.out.println("Ошибка шифра книги или книг с таким шрифтом в наличии нет!");

                        }else  bookToGet.setAvailableCopies(bookToGet.getAvailableCopies() - 1);

                        dataController.registrationData(user.getNumberOfTheTicket(), bookToGet.getCipher());

                    }

                    break;
                case "3":
                    System.out.println(userController.getInfo(user, dataController, bookController));
                    break;
                case "4":
                    System.out.println("Введите имя автора или название: ");
                    String author = reader.readLine();
                    Book[] books = bookController.findSortListBooks(author);
                    if (books.length == 0) {
                        System.out.println("Книг этого автора не найдено!");
                    } else {
                        int countB = 1;
                        for (Book b : books) {
                            System.out.println(bookController.getInfoBook(b, countB));
                            countB++;
                        }
                    }
                    break;
                case "5":
                    System.out.println("Введите шифр книг: ");
                    String codeBook = reader.readLine();
                    Book reqBook = bookController.getBook(codeBook);
                    if (reqBook == null) {
                        System.out.println("Книги с таким шрифтом не найдена!");
                    }
                    System.out.println(bookController.getInfoBook(reqBook, 1));
                    break;
                case "6":
                    System.out.println("Введите шифр книг: ");
                    String getBook = reader.readLine();
                    Book bookToGet = bookController.getBook(getBook);

                    if (bookToGet == null) {
                        System.out.println("Ошибка шифра книги или книг с таким шрифтом в наличии нет!");

                    } else {
                        bookToGet.setAvailableCopies(bookToGet.getAvailableCopies() - 1);
                        System.out.println("Списибо вы взяли книгу " + bookToGet.getTitle());
                        dataController.registrationData(user.getNumberOfTheTicket(), bookToGet.getCipher());
                    }
                    break;
                case "7":
                    flag = true;
                    break;
                default: {
                    System.out.println("КОД ОПЕРАЦИИ НЕ ВЕРНЫЙ");
                }
            }
        }
    }

    private void adminMenu() throws IOException {
        passwordHasher = new PasswordHasher();
        System.out.println("Ввидете пароль админа:");
        String password = reader.readLine();
        if (passwordHasher.verifyPassword(password)) {
            boolean flag = false;
            while (!flag) {
                System.out.println("Меню: ");
                System.out.println("1. Список читателей \n" +
                        "2. Добавить книгу \n" +
                        "3. Изминить книгу\n" +
                        "4. Удалить книгу \n" +
                        "5. Снять с обслуживание читателя\n" +
                        "6. Поиск читателя по номеру билета\n" +
                        "7. Поиск читателя по ФИО\n" +
                        "8. Выход.");

                String code = reader.readLine();
                switch (code) {
                    case "1":
                        System.out.println("Список читателей :");
                        User[] arrUsers = userController.getSortAllUsers();
                        int count = 1;
                        for (User u : arrUsers) {
                            System.out.println(userController.getInfoShort(u, count));
                            count++;
                        }
                        break;
                    case "2":
                        System.out.println("Добавить книгу");
                        System.out.println("Введите номер темы от 1  до 999:");
                        Book book = null;
                        try {
                        int topicNumber = Integer.parseInt(reader.readLine());

                        int serialNumber = bookController.getSerialNumber(topicNumber);

                        System.out.println("Введите автора:");
                        String author = reader.readLine();

                        System.out.println("Введите название:");
                        String title = reader.readLine();

                        System.out.println("Введите издателя:");
                        String publisher = reader.readLine();

                        System.out.println("Введите год публикации:");
                        int yearOfPublication = Integer.parseInt(reader.readLine());
                        //TODO проверки!
                        System.out.println("Введите общее количество копий:");
                        int totalCopies = Integer.parseInt(reader.readLine());

                        System.out.println("Введите доступное количество копий:");
                        int availableCopies = Integer.parseInt(reader.readLine());

                            book = new Book(topicNumber, serialNumber, author, title, publisher, yearOfPublication, totalCopies, availableCopies);
                        } catch (BookTopicNumberException | NumberFormatException e) {
                            System.out.println("Ошибка Ввода Номера темы!");
                        }
                        bookController.registrationBook(book);
                        System.out.println("Книга зарегистрирована!");

                        Book[] allBook = bookController.getAllBooks();
                        int countBa = 1;
                        for (Book b : allBook) {
                            if (b.getAvailableCopies() > 0) {
                                System.out.println(bookController.getInfoBook(b, countBa));
                                countBa++;
                            }
                        }
                        break;
                    case "3":
                        break;
                    case "4":
                        System.out.println("Удалить книгу");
                        System.out.println("Напишите шифр книги:");
                        String cipher = reader.readLine();
                        bookController.removeBook(cipher);
                        System.out.println("Книга удалина!");
                        break;
                    case "5":
                        System.out.println("Снять с обслуживание читателя");
                        System.out.println("Напишите номер билета:");
                        String tNumberForRemove = reader.readLine();
                        userController.removeUser(tNumberForRemove);
                        break;

                    case "6":
                        System.out.println("Поиск читателя по номеру билета");
                        System.out.println("Напишите номер билета: ");
                        String tNumberForFind = reader.readLine();
                        User user = userController.find(tNumberForFind);
                        if (user != null) {
                            System.out.println(userController.getInfo(user, dataController, bookController));
                        } else {
                            System.out.println("Пользователь на найден!");
                        }
                        break;
                    case "7":
                        System.out.println("Поиск читателя по ФИО");
                        System.out.println("Введите ФИО читателя: ");
                        String name = reader.readLine();
                        User[] arrUser = userController.getListFindFullName(name);
                        for (int i = 0; i < arrUser.length; i++) {
                            userController.getInfo(arrUser[i], dataController, bookController);
                        }
                        System.out.println("Поиск завершен!");
                        break;
                    case "8":
                        flag = true;
                        break;
                    default: {
                        System.out.println("Ошибка ввода!");
                    }
                }
            }

        } else {
            System.out.println("ОШИБКА ПАРОЛЯ!");

        }


    }

    @Override
    public void run() {
        userController = new UserController();
        dataController = new DataController();
        bookController = new BookController();

    }

    private void close() throws IOException {

        userController.loadUser();
        bookController.loadBook();
        dataController.loadData();
    }
}
