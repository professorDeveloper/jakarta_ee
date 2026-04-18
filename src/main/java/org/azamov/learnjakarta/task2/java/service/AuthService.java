package org.azamov.learnjakarta.task2.java.service;

import org.azamov.learnjakarta.task2.java.model.User;
import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AuthService {

    public AuthService() {
        try {
            DriverManager.registerDriver(new Driver());
            try (Connection connection = getConnection()) {
                connection.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS users(" +
                    "id serial PRIMARY KEY," +
                    "name VARCHAR(255) NOT NULL," +
                    "username VARCHAR(16) NOT NULL UNIQUE," +
                    "password VARCHAR(16) NOT NULL" +
                    ")"
                ).execute();
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Connection getConnection() throws Exception {
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255"
        );
    }

    public boolean login(String login, String password) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users WHERE username = ? AND password = ?"
            );
            statement.setString(1, login);
            statement.setString(2, password);
            return statement.executeQuery().next();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public User register(User user) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO users(name, username, password) VALUES (?, ?, ?) RETURNING *"
            );
            statement.setString(1, user.getName());
            statement.setString(2, user.getUsername());
            statement.setString(3, user.getPassword());
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password") );
            }
            return null;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void delete(int id) {
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?"
            );
            statement.setInt(1, id);
            statement.execute();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<>();
        try (Connection connection = getConnection()) {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM users ORDER BY id DESC"
            );
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(new User(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
                ));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return users;
    }
}
