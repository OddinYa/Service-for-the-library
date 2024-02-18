package org.course_work.entity;

import org.course_work.exception.BookTopicNumberException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class BookTest {

    @Test
    void testCreateCipher() {
        try {
            Book book = new Book(123, "John Doe", "Sample Book", "Publisher A", 2020, 10, 5);
            assertEquals("123.001", book.getCipher()); // Проверяем корректное создание шифра для книги
        } catch (BookTopicNumberException e) {
            fail("Unexpected exception was thrown");
        }
    }

    @Test
    void testInvalidTopicNumber() {
        try {
            new Book(1000, "Invalid Author", "Invalid Title", "Invalid Publisher", 2000, 5, 3);
            fail("Expected BookTopicNumberException to be thrown");
        } catch (BookTopicNumberException e) {
            assertEquals("Ошибка номера темы", e.getMessage());
        }
    }

}