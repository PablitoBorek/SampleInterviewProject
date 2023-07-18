package com.example.KamsoftProject.service;

import com.example.KamsoftProject.DAO.UserDataAccessService;
import com.example.KamsoftProject.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserService {

    private final ConversionService conversionService;
    private final UserDataAccessService userDataAccessService;

    public void executeUpdate(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            List<User> users = new ArrayList<>();
            List<String> wrongFormatLines = new ArrayList<>();

            for (String line : lines) {
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
        if (users.isEmpty() && wrongFormatLines.isEmpty() && !existingUsers.isEmpty()) {
            System.out.println("All records exist in database");
        }
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
        users.forEach(userDataAccessService::insertUser);
    }

    private List<User> findExistingUsers(List<User> users) {
        return users.stream()
                .filter(userDataAccessService::isUserInDatabase)
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
