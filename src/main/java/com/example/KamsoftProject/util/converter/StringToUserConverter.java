package com.example.KamsoftProject.util.converter;

import com.example.KamsoftProject.model.User;
import com.example.KamsoftProject.util.UserUtil;
import org.springframework.core.convert.converter.Converter;

public class StringToUserConverter implements Converter<String, User>{

    @Override
    public  User convert(String line) throws IllegalArgumentException{
        String[] parts = line.split(";");

        if (parts.length !=4) {
            throw new IllegalArgumentException("Invalid input arguments");
        }

        String firstName = parts[0];
        String lastName = parts[1];
        int age = Integer.parseInt(parts[2]);
        String emailAddress = parts[3];

        if (UserUtil.isValidName(firstName) && UserUtil.isValidName(lastName)
                && UserUtil.isValidEmail(emailAddress) && UserUtil.isValidAge(age)) {
            return new User(firstName, lastName, age, emailAddress);
        } else {
            throw new IllegalArgumentException("Invalid field format");
        }
    }
}
