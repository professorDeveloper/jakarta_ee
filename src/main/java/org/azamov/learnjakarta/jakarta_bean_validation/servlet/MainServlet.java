package org.azamov.learnjakarta.jakarta_bean_validation.servlet;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet", value = "/")
public class MainServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookDao.findAll());
        req.getRequestDispatcher("/views/main.jsp").forward(req, resp);
    }
}
