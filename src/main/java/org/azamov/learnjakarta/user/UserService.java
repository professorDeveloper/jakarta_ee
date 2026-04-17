package org.azamov.learnjakarta.user;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    public User create(User user) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement(
                    "insert into users (username) values (?) returning user_id"
            );
            statement.setString(1, user.getUsername());
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getString("user_id"));
            }
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public User findById(String id) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement(
                    "select * from users where user_id = ?"
            );
            statement.setString(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return User.builder()
                        .id(resultSet.getString("user_id"))
                        .username(resultSet.getString("username"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<User> getAll() {
        var users = new ArrayList<User>();
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("select * from users");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                users.add(User.builder()
                        .id(resultSet.getString("user_id"))
                        .username(resultSet.getString("username"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User update(User user) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement(
                    "update users set username = ? where user_id = ?"
            );
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getId());
            statement.execute();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String id) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement(
                    "delete from users where user_id = ?"
            );
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
