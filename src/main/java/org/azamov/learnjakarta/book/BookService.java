package org.azamov.learnjakarta.book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class BookService {
    public Integer save(Book book) {
        try {

            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255");
            Statement statement = connection.createStatement();
            var rs = statement.executeQuery("insert into book(title,pages) values('" + book.getTitle() + "'," + book.getPages() + ") returning id");
            if (rs.next()) {
                return rs.getInt("id");
            }
            return -1;
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public void update(Book book) {
        try {

            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255");
            var statement = connection.prepareStatement("update  book set title =?, pages =? where id=?;");
            statement.setString(1, book.getTitle());
            statement.setInt(2, book.getPages());
            statement.setInt(3, book.getId());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void delete(Integer id) {
        try {

            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255");
            var statement = connection.prepareStatement("delete from book where id=?;");
            statement.setInt(1, id);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public Book findById(Integer id) {
        try {
            DriverManager.registerDriver(new org.postgresql.Driver());
            Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255");
            var statement = connection.prepareStatement("select * from book where id=?;");
            statement.setInt(1, id);
            var rs = statement.executeQuery();
            if (rs.next()) {
                return Book.builder().id(rs.getInt("id")).title(rs.getString("title")).pages(rs.getInt("pages")).build();
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
