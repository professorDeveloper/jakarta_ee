package org.azamov.learnjakarta;

import java.sql.*;
import java.util.Properties;

public class JDBCExamples {
    public static void main(String[] args) throws SQLException {
        BookService service = new BookService();
        service.update(Book.builder().id(1).title("Updated Title").pages(100).build());
    }
}

