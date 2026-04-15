package org.azamov.learnjakarta.lms_system;

import org.postgresql.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class LmsDatabase {
    private static final String URL = "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "2255";
    private static volatile boolean initialized = false;

    public static Connection getConnection() throws SQLException {
        DriverManager.registerDriver(new Driver());
        var connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        ensureTables(connection);
        return connection;
    }

    private static void ensureTables(Connection connection) throws SQLException {
        if (initialized) {
            return;
        }
        synchronized (LmsDatabase.class) {
            if (initialized) {
                return;
            }
            try (Statement statement = connection.createStatement()) {
                statement.execute("""
                        create schema if not exists jdbc_example
                        """);
                statement.execute("""
                        create table if not exists groups (
                            group_id integer generated always as identity primary key,
                            group_name varchar(255) not null,
                            created_at timestamp not null,
                            student_count integer not null default 0
                        )
                        """);
                statement.execute("""
                        create table if not exists students (
                            student_id integer generated always as identity primary key,
                            created_at timestamp not null,
                            full_name varchar(255) not null,
                            age integer not null,
                            group_id integer references groups(group_id) on delete set null
                        )
                        """);
            }
            initialized = true;
        }
    }
}
