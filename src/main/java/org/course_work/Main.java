package org.course_work;


import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.exception.BookTopicNumberException;
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
            User user3 = new User('Г', "Сидоров Сидор", 2000, "пл. Революции, 2", "Школа Г");

            // Если доступ к этой точке, объекты были успешно созданы
            System.out.println("Объекты User успешно созданы");

            System.out.println(user1);
            System.out.println(user2);
           // System.out.println(user3);

            Book book1 = new Book(123, 1, "John Doe", "Sample Book", "Publisher A", 2020, 10, 5);


            Book book2 = new Book(1000, 50, "Jane Smith", "Another Book", "Publisher B", 2019, 20, 10);

            System.out.println(book1);
            System.out.println(book2);



        } catch (AccessRightsException e) {
            System.out.println("Ошибка создания объекта User: " + e.getMessage());
        } catch (BookTopicNumberException e) {
            System.out.println(e.getMessage());
        }
    }
}