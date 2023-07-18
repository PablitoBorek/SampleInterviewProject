package com.example.KamsoftProject.DAO;

import com.example.KamsoftProject.model.User;

public interface UserDataAccessService {
    boolean isUserInDatabase (User user);
    int insertUser(User user);
}
