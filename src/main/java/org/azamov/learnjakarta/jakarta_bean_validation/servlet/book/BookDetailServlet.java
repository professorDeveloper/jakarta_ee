package org.azamov.learnjakarta.jakarta_bean_validation.servlet.book;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.BookDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookDetailServlet", value = "/book/detail")
public class BookDetailServlet extends HttpServlet {
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
        req.getRequestDispatcher("/views/book/book_detail.jsp").forward(req, resp);
    }
}
