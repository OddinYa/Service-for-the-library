package org.course_work.service;

import org.course_work.entity.Book;
import org.course_work.entity.User;
import org.course_work.exception.BookTopicNumberException;
import org.course_work.struct.map.MyMap;
import org.course_work.struct.tree.Tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class BookFile implements FileWriter<Book>, FileReader<Tree>,FileLoader<Book> {
    java.io.FileWriter fw;
    BufferedWriter bufferedWriter;

    private String fileName = "bookRepo.txt";
    private File file;


    public BookFile() {
        try {
            file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new java.io.FileWriter(file, true);
            bufferedWriter = new BufferedWriter(fw);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public Tree readerFile() {
        Tree result = new Tree();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                Book book = extractDataFromLine(line);
                if (book != null) {
                    result.add(book);
                }
            }
        } catch (IOException | BookTopicNumberException e) {
            e.printStackTrace();
        }
        return result;
    }

    private Book extractDataFromLine(String line) throws BookTopicNumberException {

        int topicNumber = extractIntValue(line, "topicNumber=", ",");
        int serialNumber = extractIntValue(line, "serialNumber=", ",");
        String author = extractValue(line, "author='", "'");
        String title = extractValue(line, "title='", "'");
        String publisher = extractValue(line, "publisher='", "'");
        int yearOfPublication = extractIntValue(line, "yearOfPublication=", ",");
        int totalCopies = extractIntValue(line, "totalCopies=", ",");
        int availableCopies = extractIntValue(line, "availableCopies=", "}");

        if (topicNumber != 0 && serialNumber != 0 && author != null && title != null && publisher != null) {
            return new Book(topicNumber, serialNumber, author, title, publisher, yearOfPublication, totalCopies, availableCopies);
        }
        return null;
    }

    private String extractValue(String line, String startDelimiter, String endDelimiter) {
        int startIndex = line.indexOf(startDelimiter);
        int endIndex = line.indexOf(endDelimiter, startIndex + startDelimiter.length());

        if (startIndex != -1 && endIndex != -1) {
            return line.substring(startIndex + startDelimiter.length(), endIndex);
        }
        return null;
    }

    private int extractIntValue(String line, String startDelimiter, String endDelimiter) {
        String value = extractValue(line, startDelimiter, endDelimiter);
        if (value != null) {
            return Integer.parseInt(value.trim());
        }
        return 0;
    }


    @Override
    public void writeToFile(Book data) {
        try {
            if (file.length() != 0) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(data.toString());
            // bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeFile() {
        try {
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void load(Book[] arr) throws IOException {
        bufferedWriter.close();

        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));

        for (Book b : arr) {

            writer.write(b.toString());
            writer.newLine();
        }
        writer.close();
    }



}
