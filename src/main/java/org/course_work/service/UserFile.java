package org.course_work.service;

import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.struct.map.MyMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class UserFile implements FileWriter<User>,FileReader<MyMap>{
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

            fw = new java.io.FileWriter(file,true);
            bufferedWriter = new BufferedWriter(fw);

        }catch (IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public MyMap readerFile() {
        MyMap map = new MyMap();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] values = line.replaceAll("[{} ]", "").split(",");
                if(values.length > 1){

                User user = new User(
                        parseAccessRights(values[1]),
                        parseValue("fullName", values),
                        Integer.parseInt(parseValue("yearOfBirth", values)),
                        parseValue("address", values),
                        parseValue("placeOfWorkOrStudy", values)
                );

                   map.put(user.getNumberOfTheTicket(),user);}
                else {

                }

            }
        } catch (IOException | AccessRightsException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void writeToFile(User data) {
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
