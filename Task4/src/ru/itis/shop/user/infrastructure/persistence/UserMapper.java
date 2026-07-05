package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper {

    public User fromLine(String line) {
        String[] parts = line.split("\\|");

        return new User(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }

    public String toLine(User user) {
        return user.getId() + "|" +
                user.getName() + "|" +
                user.getEmail() + "|" +
                user.getPassword() + "|" +
                user.getProfileDescription();
    }

    public User fromDataBaseRepository(ResultSet resultSet) {
        try {
            return new User(resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getString("password"),
                    resultSet.getString("profile_description"));
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
