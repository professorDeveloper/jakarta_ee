package org.azamov.learnjakarta;

import org.azamov.learnjakarta.book.Book;
import org.azamov.learnjakarta.book.BookService;

import java.sql.*;

public class JDBCExamples {
    public static void main(String[] args) throws SQLException {
        BookService service = new BookService();
        service.update(Book.builder().id(1).title("Updated Title").pages(100).build());
    }
}

