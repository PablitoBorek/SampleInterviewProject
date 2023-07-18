package com.example.KamsoftProject.shellCommand;

import com.example.KamsoftProject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@RequiredArgsConstructor
public class UserShellCommand {

    private static final String defaultPath = "src/main/resources/Users.txt";

    private final UserService userService;

    @ShellMethod(key = "run", value = "Save all record into User table from a file, please give a path to your file (default path src/main/resources/Users.txt)")
    public void run(@ShellOption(defaultValue = defaultPath) String path) {
        userService.executeUpdate(path);
    }
}
