package org.course_work.service;

import org.course_work.entity.DataOnTheIssuanceAndAcceptanceOfBooks;
import org.course_work.struct.linkedList.List;

import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class DataIABFile implements FileWriter<DataOnTheIssuanceAndAcceptanceOfBooks>,FileReader<List> {
    java.io.FileWriter fw;
    BufferedWriter bufferedWriter;

    private String fileName = "dataRepo.txt";

    private File file;
    @Override
    public List readerFile() {
        return null;
    }

    @Override
    public void writeToFile(DataOnTheIssuanceAndAcceptanceOfBooks data) {
        try {
            if (file.length() != 0) {
                bufferedWriter.newLine();
            }
            bufferedWriter.write(data.toString());
            bufferedWriter.newLine();

        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
