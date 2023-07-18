package com.example.kamesoftproject.DAO;

import com.example.kamesoftproject.model.User;

import java.util.Optional;

public interface UserDao {
    boolean isUserInDatabase (User user);
    int insertUser(User user);
}
