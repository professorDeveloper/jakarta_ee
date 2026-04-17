package org.azamov.learnjakarta.user;

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
                        create table if not exists users (
                            user_id integer generated always as identity primary key,
                            username varchar(255) not null
                        )
                        """);
            }
            initialized = true;
        }
    }
}
