package ru.itis.shop.user.infrastructure.persistence;

import ru.itis.shop.user.domain.User;
import ru.itis.shop.user.repository.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryJdbcImpl implements UserRepository {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/user_accs";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "qwerty333";

    private final UserMapper userMapper;

    public UserRepositoryJdbcImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public void save(User user) {

    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
            try (Statement statement = connection.createStatement()) {
                List<User> allUsers = new ArrayList<>();
                try (ResultSet resultSet = statement.executeQuery("select * from accounts")) {
                    while (resultSet.next()) {
                        User user = userMapper.fromDataBaseRepository(resultSet);
                        allUsers.add(user);
                    }
                }
                return allUsers;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateUser(User user) {

    }
}
