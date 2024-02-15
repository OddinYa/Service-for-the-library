package org.course_work.service;

import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class UserFile implements FileWriter<User>,FileReader{
    java.io.FileWriter fw;
    BufferedWriter bufferedWriter;

    private String fileName = "userRepo.txt";

    private File file;

    public UserFile(){
        try {
            file = new File(fileName);

            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new java.io.FileWriter(file);
            bufferedWriter = new BufferedWriter(fw);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void readerFile() {
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {

                String[] values = line.replaceAll("[{} ]", "").split(",");


                User user = new User(
                        parseAccessRights(values[1]),
                        parseValue("fullName", values),
                        Integer.parseInt(parseValue("yearOfBirth", values)),
                        parseValue("address", values),
                        parseValue("placeOfWorkOrStudy", values)
                );

                System.out.println(user);

            }
        } catch (IOException | AccessRightsException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void writeToFile(User data) {
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

    private char parseAccessRights(String value) {
        return value.charAt(value.indexOf('=') + 1);
    }

    private String parseValue(String key, String[] values) {
        for (String value : values) {
            if (value.startsWith(key)) {
                return value.substring(value.indexOf('=') + 1);
            }
        }
        return null;
    }
}
