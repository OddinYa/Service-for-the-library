package org.course_work;


import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.exception.BookTopicNumberException;
import org.course_work.service.UserFile;
import org.course_work.view.console.ConsoleDisplay;

public class Main {
    public static void main(String[] args) {
     // ConsoleDisplay consoleDisplay = new ConsoleDisplay();
        DataOnTheIssuanceAndAcceptanceOfBooks data = new DataOnTheIssuanceAndAcceptanceOfBooks(
                "Ч0003-23",
                "001.001");

        System.out.println(data);

        data.setReturnDate();

        System.out.println(data);

        try {
            User user1 = new User('А', "Иванов Иван", 1990, "ул. Пушкина, 10", "Компания А");
            User user2 = new User('Ч', "Петров Петр", 1985, "пр. Ленина, 5", "Университет Ч");


            // Если доступ к этой точке, объекты были успешно созданы
            System.out.println("Объекты User успешно созданы");
            System.out.println(user1.hashCode()%16);
            System.out.println(user2.hashCode()%16);
            UserFile f = new UserFile();

            f.writeToFile(user1);
            f.writeToFile(user2);
            f.closeFile();

            f.readerFile();

            System.out.println(user1);
            System.out.println(user2);
           // System.out.println(user3);

            Book book1 = new Book(1, "John Doe", "Sample Book", "Publisher A", 2020, 10, 5);


            Book book2 = new Book(1,  "Jane Smith", "Another Book", "Publisher B", 2019, 20, 10);

            System.out.println(book1.hashCode());
            System.out.println(book2.hashCode());
            for (int i = 0; i < 100 ; i++){
                Book book20 = new Book(1+i, "John Doe", "Sample Book", "Publisher A", 2020+i, 10, 5);
                System.out.println(book20.hashCode());
            }


        } catch (AccessRightsException e) {
            System.out.println("Ошибка создания объекта User: " + e.getMessage());
        } catch (BookTopicNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}