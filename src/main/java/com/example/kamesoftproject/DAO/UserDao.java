package com.example.kamesoftproject.DAO;

import com.example.kamesoftproject.model.User;

public interface UserDao {
    boolean isUserInDatabase (User user);
    int insertUser(User user);
}
