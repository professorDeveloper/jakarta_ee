package org.azamov.learnjakarta.jakarta_bean_validation.servlet.admin;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.BookDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AdminBookDeleteServlet", value = "/admin/book/delete")
public class AdminBookDeleteServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Book book = bookDao.findByID(id);
        if (book == null) {
            resp.sendRedirect(req.getContextPath() + "/book/list");
            return;
        }
        req.setAttribute("book", book);
        req.getRequestDispatcher("/views/admin/delete_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        bookDao.deleteById(id);
        resp.sendRedirect(req.getContextPath() + "/book/list");
    }
}
