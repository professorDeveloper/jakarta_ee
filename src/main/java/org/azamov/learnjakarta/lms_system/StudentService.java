package org.azamov.learnjakarta.lms_system;

import java.sql.Timestamp;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class StudentService {
    private Integer parseInteger(String value) {
        if (value == null || value.isBlank()) {
            return null;
        }
        return Integer.parseInt(value);
    }

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


    public List<Student> getAll() {
        try {
            var students = new ArrayList<Student>();
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("select * from students");
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var groupId = resultSet.getObject("group_id") != null ? String.valueOf(resultSet.getInt("group_id")) : null;
                students.add(new Student(
                        resultSet.getString("student_id"),
                        resultSet.getTimestamp("created_at").toLocalDateTime().toString().replace('T', ' '),
                        resultSet.getString("full_name"),
                        resultSet.getInt("age"),
                        groupId
                ));
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Student> findByGroupId(String groupId) {
        try {
            var students = new ArrayList<Student>();
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("select * from students where group_id=?");
            statement.setInt(1, Integer.parseInt(groupId));
            var resultSet = statement.executeQuery();
            while (resultSet.next()) {
                var currentGroupId = resultSet.getObject("group_id") != null ? String.valueOf(resultSet.getInt("group_id")) : null;
                students.add(new Student(
                        resultSet.getString("student_id"),
                        resultSet.getTimestamp("created_at").toLocalDateTime().toString().replace('T', ' '),
                        resultSet.getString("full_name"),
                        resultSet.getInt("age"),
                        currentGroupId
                ));
            }
            return students;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student findById(String id) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("select * from students where student_id=?");
            statement.setString(1, id);
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                var groupId = resultSet.getObject("group_id") != null ? String.valueOf(resultSet.getInt("group_id")) : null;
                return new Student(
                        resultSet.getString("student_id"),
                        resultSet.getTimestamp("created_at").toLocalDateTime().toString().replace('T', ' '),
                        resultSet.getString("full_name"),
                        resultSet.getInt("age"),
                        groupId
                );
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Student save(Student student) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("insert into students (created_at, full_name, age, group_id) values(?, ?, ?, ?) returning student_id");
            statement.setTimestamp(1, parseTimestamp(student.getCreatedAt()));
            statement.setString(2, student.getFullName());
            statement.setInt(3, student.getAge());
            if (parseInteger(student.getGroupId()) == null) {
                statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                statement.setInt(4, parseInteger(student.getGroupId()));
            }
            var resultSet = statement.executeQuery();
            if (resultSet.next()) {
                student.setId(resultSet.getString("student_id"));
            }
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Student update(Student student) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("update students set created_at=?, full_name=?, age=?, group_id=? where student_id=?");
            statement.setTimestamp(1, parseTimestamp(student.getCreatedAt()));
            statement.setString(2, student.getFullName());
            statement.setInt(3, student.getAge());
            if (parseInteger(student.getGroupId()) == null) {
                statement.setNull(4, java.sql.Types.INTEGER);
            } else {
                statement.setInt(4, parseInteger(student.getGroupId()));
            }
            statement.setString(5, student.getId());
            statement.execute();
            return student;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete(String id) {
        try {
            var connection = LmsDatabase.getConnection();
            var statement = connection.prepareStatement("delete from students where student_id=?");
            statement.setString(1, id);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

