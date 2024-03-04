package org.course_work.service;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.struct.linkedList.List;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class DataIABFile implements FileWriter<DataOnTheIssuanceAndAcceptanceOfBooks>, FileReader<List>, FileLoader<DataOnTheIssuanceAndAcceptanceOfBooks> {
    private java.io.FileWriter fw;
    private BufferedWriter bufferedWriter;
    private List list;

    private String fileName = "dataRepo.txt";

    private File file;

    public DataIABFile() {
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
    public List readerFile() {
        list = new List();

        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 4) {
                    DataOnTheIssuanceAndAcceptanceOfBooks data = extractDataFromLine(line);
                    if (data != null) {
                        list.add(data);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

    private DataOnTheIssuanceAndAcceptanceOfBooks extractDataFromLine(String line) {
        DataOnTheIssuanceAndAcceptanceOfBooks data = null;

        String libraryCardNumber = extractValue(line, "libraryCardNumber='", "'");
        String cipher = extractValue(line, " cipher='", "'");
        String dateOfIssue = extractValue(line, " dateOfIssue='", "'");
        String returnDate = extractValue(line, " returnDate='", "'");

        if (libraryCardNumber != null && cipher != null && dateOfIssue != null) {
            data = DataOnTheIssuanceAndAcceptanceOfBooks.buildData(libraryCardNumber, cipher, dateOfIssue, returnDate);
        }

        return data;
    }

    private String extractValue(String line, String startDelimiter, String endDelimiter) {

        int startIndex = line.indexOf(startDelimiter);
        int endIndex = line.indexOf(endDelimiter, startIndex + startDelimiter.length());

        if (startIndex != -1 && endIndex != -1) {
            return line.substring(startIndex + startDelimiter.length(), endIndex);
        }
        return null;  // Возвращаем null если значение не найдено
    }


    @Override
    public void writeToFile(DataOnTheIssuanceAndAcceptanceOfBooks data) {
        try {
            if (file.length() != 0) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(data.toString());

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
    public void load(DataOnTheIssuanceAndAcceptanceOfBooks[] arr) throws IOException {
        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));

        for (DataOnTheIssuanceAndAcceptanceOfBooks d : arr) {

            writer.write(d.toString());
            writer.newLine();
        }
        writer.close();
    }
}

