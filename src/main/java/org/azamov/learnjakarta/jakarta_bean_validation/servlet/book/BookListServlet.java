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
    private static final int PAGE_SIZE = 10;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int page = 0;
        try { page = Math.max(0, Integer.parseInt(req.getParameter("page"))); } catch (Exception ignored) {}

        long total = bookDao.count();
        long totalPages = (total + PAGE_SIZE - 1) / PAGE_SIZE;
        if (page >= totalPages && totalPages > 0) page = (int) totalPages - 1;

        req.setAttribute("books", bookDao.findPage(page, PAGE_SIZE));
        req.setAttribute("page", page);
        req.setAttribute("totalPages", totalPages);
        req.getRequestDispatcher("/views/book/book_list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(405);
    }
}
