package com.example.kamesoftproject.service;

import com.example.kamesoftproject.DAO.UserDao;
import com.example.kamesoftproject.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class UserDataAccessService implements UserDao {

    private final JdbcTemplate jdbcTemplate;

    public UserDataAccessService(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public boolean isUserInDatabase(User user) {
        String query = "SELECT * FROM users WHERE LastName = ? AND FirstName = ? " +
                "AND Email = ? AND Age = ?";
        Object[] params = {user.getSurname(), user.getName(), user.getEmailAdress(), user.getAge()};

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(query, params);

        return rowSet.next();
    }

    @Override
    public int insertUser(User user) {
        String sql = "INSERT INTO users (LastName, FirstName, Email, Age) VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql, user.getSurname(), user.getName(), user.getEmailAdress(), user.getAge());
    }
}
