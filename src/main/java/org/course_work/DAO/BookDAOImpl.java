package org.course_work.DAO;

import org.course_work.entity.Book;
import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.exception.BookTopicNumberException;
import org.course_work.service.BMSearch;
import org.course_work.service.BookFile;
import org.course_work.service.MergeSort;
import org.course_work.struct.tree.Tree;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class BookDAOImpl implements BookDAO {
    private Tree tree;

    private MergeSort<Book> bookMergeSort;
    private BookFile fileBook;

    public BookDAOImpl() {

        fileBook = new BookFile();
        tree = fileBook.readerFile();

    }

    @Override
    public void addNewBook(Book book) {
        try {
            tree.add(book);
            //fileBook.writeToFile(book);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeBook(String cipher) {
        // tree.delete(findBookByCipher(cipher));
    }

    @Override
    public Book[] getAllBooks() {
        bookMergeSort = new MergeSort<>();

        Book[] books = tree.traversal();
        Book[] sorted = bookMergeSort.sort(books);

        return sorted;
    }

    @Override
    public void clearBookData() {

    }

    @Override
    public Book findBookByCipher(String cipher) {
       Book result = tree.get(cipher);
       return result;
    }

    @Override
    public Book[] findBooksByAuthorOrTitle(String searchQuery) {
        Book[] temp = tree.traversal();
        BMSearch bmSearch = new BMSearch();
        Book[] result = new Book[temp.length];
        int count = 0;
        for (Book book : temp) {
            String text = book.getAuthor() + book.getTitle();
            if (bmSearch.job(text, searchQuery)) {
                result[count] = book;
                count++;
            }
        }
        return result;
    }

    public void close() {
        fileBook.closeFile();
    }

    public int getSerialNumber(int topicNumber){
        int serialNumber = 1;
        while (true){
            String check = createCipher(topicNumber,serialNumber);
            Book book = tree.get(check);
           if(book==null){
               return serialNumber;
           }else {
               serialNumber++;
           }
        }
    }

    private String createCipher(int topicNumber,int serialNumber){

        StringBuilder stringBuilder = new StringBuilder();
        String topic = String.format("%03d", topicNumber);
        stringBuilder.append(topic);
        stringBuilder.append(".");
        String serial = String.format("%03d", serialNumber);
        stringBuilder.append(serial);

        return stringBuilder.toString();
    }
    public void loadBooks() throws IOException {
       Book[] arr = tree.traversal();
        fileBook.load(arr);
    }

}
