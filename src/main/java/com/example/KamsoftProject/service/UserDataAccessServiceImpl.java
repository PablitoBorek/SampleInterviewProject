package com.example.KamsoftProject.service;

import com.example.KamsoftProject.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserDataAccessServiceImpl implements com.example.KamsoftProject.DAO.UserDataAccessService {

    private final JdbcTemplate jdbcTemplate;

    @Override
    public boolean isUserInDatabase(User user) {
        String query = "SELECT * FROM users WHERE LastName = ? AND FirstName = ? " +
                "AND Email = ? AND Age = ?";
        Object[] params = {user.surname(), user.name(), user.emailAddress(), user.age()};

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, params);

        return rowSet.next();
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO users (LastName, FirstName, Email, Age) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql, user.surname(), user.name(), user.emailAddress(), user.age());
    }
}
