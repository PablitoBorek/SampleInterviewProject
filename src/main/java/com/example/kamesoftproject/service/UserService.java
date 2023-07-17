package com.example.kamesoftproject.service;

import com.example.kamesoftproject.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private ConversionService conversionService;

    private  static final String pathToDatabase = "jdbc:h2:~/test";

    public void executeUpdate(String path) {
        try (Connection connection = DriverManager.getConnection(pathToDatabase)) {

            BufferedReader reader = new BufferedReader(new FileReader(path));
            String line;
            List<User> users = new ArrayList<>();
            List<String> wrongFormatLines = new ArrayList<>();
            while ((line = reader.readLine()) != null) {
                try {
                    User user = conversionService.convert(line, User.class);
                    users.add(user);
                } catch (ConversionFailedException e) {
                    wrongFormatLines.add(line);
                }
            }
             // Find existing users
            // save users to database

            reader.close();
            sendMessage(users, wrongFormatLines);

          //  connection.prepareStatement("insert into users VALUES ('John','Smith',25,'john.smith@example.com')");

        } catch (IOException | SQLException e) {
            System.out.println("Failed to load file from path " + path);
        }



    }

    private void sendMessage(List<User> users, List<String> wrongFormatLines) {
        if (users.isEmpty()  && wrongFormatLines.isEmpty()) {
            System.out.println("File is empty");
        } else if (users.isEmpty()) {
            System.out.println("Failed to save any record to database");
        } else {
            System.out.println("Successfully saved " + users.size() + " records in database ");
            System.out.println("All failed records to save :");
            wrongFormatLines.forEach(System.out::println);
        }
    }

    private void saveToDabase() {

    }

    //private List<User> findExistingUsers()
}
