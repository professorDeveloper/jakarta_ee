package org.azamov.learnjakarta.task4.servlet;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import lombok.val;
import org.azamov.learnjakarta.task4.entites.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookServlet", urlPatterns = "/book")
public class BookServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("orm_example");
        EntityManager manager = factory.createEntityManager();
        manager.getTransaction().begin();
        val javaAlgorithms = Book.builder()
                .id(12L)
                .title("Java algorithms")
                .pages(123)
                .build();
        manager.persist(javaAlgorithms);
        manager.close();
        factory.close();


    }
}
