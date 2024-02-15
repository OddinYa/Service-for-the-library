package org.course_work.entity;

import org.course_work.exception.BookTopicNumberException;

import java.util.Objects;

public class Book {
    private String cipher;
    private int topicNumber;

    private static int lastAssignedNumber = 0;
    private int serialNumber;

    private String author;


    private String title;
    private String publisher;
    private int yearOfPublication;
    private int totalCopies;
    private int availableCopies;

    public Book(int topicNumber, String author, String title, String publisher, int yearOfPublication, int totalCopies, int availableCopies) throws BookTopicNumberException {


        if(checkTopicNumbed(topicNumber)){
            this.topicNumber = topicNumber;

            this.author = author;
            this.title = title;
            this.publisher = publisher;
            this.yearOfPublication = yearOfPublication;
            this.totalCopies = totalCopies;
            this.availableCopies = availableCopies;

            cipher = createCipher();}
        else {
            throw new BookTopicNumberException("Ошибка номера темы");
        }


    }
    private String createCipher()throws BookTopicNumberException{

        StringBuilder stringBuilder = new StringBuilder();
        String topic = String.format("%03d",topicNumber);
        stringBuilder.append(topic);
        stringBuilder.append(".");
        lastAssignedNumber++;
        this.serialNumber = lastAssignedNumber;
        String serial = String.format("%03d",serialNumber);
        stringBuilder.append(serial);

        return stringBuilder.toString();
    }

    private boolean checkTopicNumbed(int topicNumber){
        return topicNumber < 999 && topicNumber > 0;
    }

    public String getCipher() {
        return cipher;
    }

    public int getTopicNumber() {
        return topicNumber;
    }

    public void setTopicNumber(Integer topicNumber) {
        this.topicNumber = topicNumber;
    }

    public int getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(int serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }

    public int getTotalCopies() {
        return totalCopies;
    }

    public void setTotalCopies(int totalCopies) {
        this.totalCopies = totalCopies;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void setAvailableCopies(int availableCopies) {
        this.availableCopies = availableCopies;
    }

    @Override
    public String toString() {
        return "Book{" +
                "cipher='" + cipher + '\'' +
                ", topicNumber=" + topicNumber +
                ", serialNumber=" + serialNumber +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publisher='" + publisher + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", totalCopies=" + totalCopies +
                ", availableCopies=" + availableCopies +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return topicNumber == book.topicNumber &&
                serialNumber == book.serialNumber &&
                yearOfPublication == book.yearOfPublication &&
                totalCopies == book.totalCopies &&
                availableCopies == book.availableCopies &&
                cipher.equals(book.cipher) &&
                Objects.equals(author, book.author) &&
                Objects.equals(title, book.title) &&
                Objects.equals(publisher, book.publisher);
    }

    //TODO
    @Override
    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = prime * result + topicNumber;
        result = prime * result + serialNumber;
        return result;
    }
}
