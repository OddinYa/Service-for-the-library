package org.course_work.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class PasswordHasher {

    private String fileName = "pass.txt";


    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = digest.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {

            e.getStackTrace();
        }
        return null;
    }

    public boolean verifyPassword(String inputPassword) {
        String storedHashedPassword = readPasswordFromFile(fileName);
        if (storedHashedPassword != null) {
            String inputHash = hashPassword(inputPassword);
            return inputHash != null && inputHash.equals(hashPassword(storedHashedPassword));
        } else {
            // Обработка случая, когда не удалось прочитать пароль из файла
            return false;
        }
    }


    private String readPasswordFromFile(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/" + filename);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            return br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
