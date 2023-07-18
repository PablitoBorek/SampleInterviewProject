package com.example.kamesoftproject.service;

import com.example.kamesoftproject.DAO.UserDao;
import com.example.kamesoftproject.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private final ConversionService conversionService;
    private final UserDao userDao;

    public UserService(ConversionService conversionService, UserDao userDao) {
        this.conversionService = conversionService;
        this.userDao = userDao;
    }

    private final static Logger log = LoggerFactory
            .getLogger(UserService.class);


    public void executeUpdate(String path) {
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            List<User> users = new ArrayList<>();
            List<String> wrongFormatLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                User user = convertIntoUser(line);
                if (user == null) {
                    wrongFormatLines.add(line);
                } else {
                    users.add(user);
                }
            }
            List<User> userExistingInDatabase = findExistingUsers(users);
            users.removeAll(userExistingInDatabase);
            saveUsersIntoDatabase(users);

            sendMessage(users, wrongFormatLines, userExistingInDatabase);

        } catch (IOException e) {
            log.error("Failed to load file from path " + path);
        }
    }

    private void sendMessage(List<User> users, List<String> wrongFormatLines, List<User> existingUsers) {
        if (users.isEmpty() && wrongFormatLines.isEmpty()) {
            System.out.println("File is empty");
        } else if (users.isEmpty()) {
            System.out.println("Failed to save any record to database");
        } else {
            System.out.println("Successfully saved " + users.size() + " records in database ");
            System.out.println("Lines with wrong format :");
            wrongFormatLines.forEach(System.out::println);
            if (!existingUsers.isEmpty()) {
                System.out.println("Record already existing in database ");
                existingUsers.forEach(System.out::println);
            }

        }
    }

    private void saveUsersIntoDatabase(List<User> users) {
        users.forEach(userDao::insertUser);
    }

    private List<User> findExistingUsers(List<User> users) {
        return users.stream()
                .filter(userDao::isUserInDatabase)
                .toList();
    }

    private User convertIntoUser(String line) {
        try {
            return conversionService.convert(line, User.class);
        } catch (ConversionFailedException e) {
            return null;
        }
    }
}
