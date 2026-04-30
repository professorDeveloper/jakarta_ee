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
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "AdminBookCreateServlet", value = "/admin/book/create")
@MultipartConfig(maxFileSize = 20 * 1024 * 1024)
public class AdminBookCreateServlet extends HttpServlet {
    private final BookDao bookDao = new BookDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/admin/create_book.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String author = req.getParameter("author");
        String description = req.getParameter("description");

        Map<String, String> errors = new HashMap<>();
        if (title == null || title.isBlank()) errors.put("title_err", "Sarlavha kiritish shart");
        if (author == null || author.isBlank()) errors.put("author_err", "Muallif kiritish shart");

        if (!errors.isEmpty()) {
            errors.forEach(req::setAttribute);
            req.getRequestDispatcher("/views/admin/create_book.jsp").forward(req, resp);
            return;
        }

        Upload cover = null;
        Upload pdf = null;

        Part coverPart = req.getPart("cover");
        if (coverPart != null && coverPart.getSize() > 0) {
            cover = FileHelper.save(coverPart);
        }

        Part pdfPart = req.getPart("pdf");
        if (pdfPart != null && pdfPart.getSize() > 0) {
            pdf = FileHelper.save(pdfPart);
        }

        Book book = Book.childMethodBuilder()
                .title(title)
                .author(author)
                .description(description)
                .cover(cover)
                .pdf(pdf)
                .build();

        bookDao.save(book);
        resp.sendRedirect(req.getContextPath() + "/book/list");
    }
}
