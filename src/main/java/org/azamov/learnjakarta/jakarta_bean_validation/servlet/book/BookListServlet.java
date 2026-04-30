package org.azamov.learnjakarta.jakarta_bean_validation.servlet.book;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.BookDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookListServlet", value = "/book/list")
public class BookListServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("books", bookDao.findAll());
        req.getRequestDispatcher("/views/book/book_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(405, "Method not allowed");
    }
}
