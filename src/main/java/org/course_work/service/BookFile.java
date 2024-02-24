package org.course_work.service;

import org.course_work.entity.Book;
import org.course_work.exception.BookTopicNumberException;
import org.course_work.struct.map.MyMap;
import org.course_work.struct.tree.Tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class BookFile implements FileWriter<Book>,FileReader<Tree> {
    java.io.FileWriter fw;
    BufferedWriter bufferedWriter;

     private  String fileName = "BookRepo.txt";
     private  File file;

    public BookFile(){
        try {
            file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new java.io.FileWriter(file,true);
            bufferedWriter = new BufferedWriter(fw);

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private String parseValue(String key, String[] values) {
        for (String value : values) {
            if (value.startsWith(key)) {
                return value.substring(value.indexOf('=') + 1);
            }
        }
        return null;
    }

    @Override
    public Tree readerFile() {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.replaceAll("[{} ]", "").split(",");

                Book book = new Book(
                        Integer.parseInt(parseValue("topicNumber",values)),
                        parseValue("author",values),
                        parseValue("title",values),
                        parseValue("publisher",values),
                        Integer.parseInt(parseValue("yearOfPublication", values)),
                        Integer.parseInt(parseValue("totalCopies", values)),
                        Integer.parseInt(parseValue("availableCopies", values))
                );

                System.out.println(book); // delete

            }
        } catch (IOException | BookTopicNumberException e ) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void writeToFile(Book data) {
        try {
            bufferedWriter.write(data.toString());
            bufferedWriter.newLine();
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
}
