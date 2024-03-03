package org.course_work.service;

import org.course_work.entity.User;
import org.course_work.exception.AccessRightsException;
import org.course_work.struct.map.MyMap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;

public class UserFile implements FileWriter<User>, FileReader<MyMap>, FileLoader<User> {
    java.io.FileWriter fw;
    BufferedWriter bufferedWriter;

    private String fileName = "userRepo.txt";

    private File file;

    public UserFile() {
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
    public MyMap readerFile() {
        MyMap map = new MyMap();
        try (BufferedReader br = new BufferedReader(new java.io.FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null) {

                String[] values = line.split(",");
                if (values.length > 1) {

                    User user = extractDataFromLine(line);
                    if (user != null) {
                        map.put(user.getNumberOfTheTicket(), user);
                    }
                } else {

                }

            }
        } catch (IOException | AccessRightsException e) {
            e.printStackTrace();
        }
        return map;
    }

    private User extractDataFromLine(String line) throws AccessRightsException {
        User user = null;
        String numberOfTheTicket = extractValue(line, "numberOfTheTicket='", "'");
        char accessRights = extractAccessRights(line);
        int numbOfRegistration = Integer.parseInt(extractValue(line, "numbOfRegistration=", ","));
        String fullName = extractValue(line, "fullName='", "'");
        int yearOfBirth = Integer.parseInt(extractValue(line, "yearOfBirth=", ","));
        String address = extractValue(line, "address='", "'");
        String placeOfWorkOrStudy = extractValue(line, "placeOfWorkOrStudy='", "'");

        if (numberOfTheTicket != null && fullName != null) {
            user = new User(numberOfTheTicket, accessRights, numbOfRegistration, fullName, yearOfBirth, address, placeOfWorkOrStudy);
        }

        return user;
    }

    private String extractValue(String line, String startDelimiter, String endDelimiter) {

        int startIndex = line.indexOf(startDelimiter);
        int endIndex = line.indexOf(endDelimiter, startIndex + startDelimiter.length());

        if (startIndex != -1 && endIndex != -1) {
            return line.substring(startIndex + startDelimiter.length(), endIndex);
        }
        return null;
    }

    private char extractAccessRights(String line) {
        int index = line.indexOf("accessRights=");
        if (index != -1) {
            return line.charAt(index + 13);
        }
        return ' ';
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

    @Override
    public void load(User[] arr) throws IOException {
        bufferedWriter.close();

        BufferedWriter writer = new BufferedWriter(new java.io.FileWriter(fileName));

        for (User u : arr) {

            writer.write(u.toString());
            writer.newLine();
        }
        writer.close();
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
