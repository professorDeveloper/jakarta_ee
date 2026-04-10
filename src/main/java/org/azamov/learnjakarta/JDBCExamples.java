package org.azamov.learnjakarta;

import java.sql.*;
import java.util.Properties;

public class JDBCExamples {
    public static void main(String[] args) throws SQLException {
        DriverManager.registerDriver(new org.postgresql.Driver());
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "2255");
        Statement statement = connection.createStatement();
        var execute = statement.executeQuery("select version();");
        execute.next();
        System.out.println(execute.getString(1));
    }
}
