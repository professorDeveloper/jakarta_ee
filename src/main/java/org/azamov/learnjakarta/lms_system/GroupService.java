package org.azamov.learnjakarta.lms_system;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

public class GroupService {
    private Timestamp parseTimestamp(String value) {
        if (value == null || value.isBlank()) {
            return Timestamp.valueOf(LocalDateTime.now().withSecond(0).withNano(0));
        }
        try {
            return Timestamp.valueOf(value);
        } catch (IllegalArgumentException ignored) {
        }
        try {
            return Timestamp.valueOf(LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        } catch (DateTimeParseException ignored) {
        }
        try {
            return Timestamp.valueOf(LocalDateTime.parse(value, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
        } catch (DateTimeParseException ignored) {
        }
        throw new IllegalArgumentException("Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]");
    }

    public Group create(Group group) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("insert into groups (group_name, created_at, student_count) values(?, ?, ?) returning group_id");
            statement.setString(1, group.getName());
            statement.setTimestamp(2, parseTimestamp(group.getCreatedAt()));
            statement.setInt(3, group.getStudentCount());
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                group.setId(resultSet.getString("group_id"));
            }
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void closeResources(java.sql.Connection connection, java.sql.Statement statement, java.sql.ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Group findById(String id) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("select * from groups g where g.group_id =?; ");
            statement.setString(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Group.builder()
                        .id(resultSet.getString("group_id"))
                        .name(resultSet.getString("group_name"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime().toString().replace('T', ' '))
                        .studentCount(resultSet.getInt("student_count"))
                        .build();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Group> getAll() {
        var groups = new ArrayList<Group>();
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("select * from groups");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                groups.add(Group.builder()
                        .id(resultSet.getString("group_id"))
                        .name(resultSet.getString("group_name"))
                        .createdAt(resultSet.getTimestamp("created_at").toLocalDateTime().toString().replace('T', ' '))
                        .studentCount(resultSet.getInt("student_count"))
                        .build());
            }
            return groups;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return groups;
    }

    public Group update(Group group) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("update groups set group_name = ?, created_at = ?, student_count = ? where group_id = ?");
            statement.setString(1, group.getName());
            statement.setTimestamp(2, parseTimestamp(group.getCreatedAt()));
            statement.setInt(3, group.getStudentCount());
            statement.setString(4, group.getId());
            statement.execute();
            return group;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String id) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("delete from groups where group_id = ?");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
