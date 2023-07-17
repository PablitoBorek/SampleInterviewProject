package com.example.kamesoftproject.util;

import java.util.regex.Pattern;

public class UserUtil {

    private static final String REGEX = "^[\\p{L}]{2,30}$";
    private static final String emailPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

    public static boolean isValidName(String name) {
        return Pattern.matches(REGEX, name);
    }

    public static boolean isValidEmail(String email) {return Pattern.matches(emailPattern, email);}
}
