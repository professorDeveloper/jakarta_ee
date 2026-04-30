package org.azamov.learnjakarta.jakarta_bean_validation.servlet.admin;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.BookDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.Book;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.Upload;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.FileHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet(name = "AdminBookUpdateServlet", value = "/admin/book/update")
@MultipartConfig(maxFileSize = 20 * 1024 * 1024)
public class AdminBookUpdateServlet extends HttpServlet {
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
        req.getRequestDispatcher("/views/admin/update_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String description = req.getParameter("description");

        Book book = bookDao.findByID(id);
        if (book != null) {
            book.setTitle(title);
            book.setAuthor(author);
            book.setDescription(description);

            Part coverPart = req.getPart("cover");
            if (coverPart != null && coverPart.getSize() > 0) {
                book.setCover(FileHelper.save(coverPart));
            }

            Part pdfPart = req.getPart("pdf");
            if (pdfPart != null && pdfPart.getSize() > 0) {
                book.setPdf(FileHelper.save(pdfPart));
            }

            bookDao.update(book);
        }

        resp.sendRedirect(req.getContextPath() + "/book/list");
    }
}
