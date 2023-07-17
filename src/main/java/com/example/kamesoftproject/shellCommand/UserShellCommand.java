package com.example.kamesoftproject.shellCommand;

import com.example.kamesoftproject.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class UserShellCommand {

    private static final String defaultPath = "src/main/resources/Users.txt";

    @Autowired
    private UserService userService;

    private static Logger log = LoggerFactory
            .getLogger(UserShellCommand.class);

    @ShellMethod(key = "run", value = "Save all record into User table from a file, please give a path to your file (default path src/main/resources/Users.txt)")
    public void run(@ShellOption(defaultValue = defaultPath) String path) {
        userService.executeUpdate(path);
    }
}
