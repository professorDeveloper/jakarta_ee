package org.azamov.learnjakarta.jakarta_bean_validation.servlet;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.UploadDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.Upload;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.FileHelper;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@WebServlet(name = "UploadServlet", value = "/uploads/*")
public class UploadServlet extends HttpServlet {
    private final UploadDao uploadDao = new UploadDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.length() <= 1) {
            resp.sendError(404);
            return;
        }
        String id = pathInfo.substring(1);

        Upload upload = uploadDao.findByID(id);
        if (upload == null) {
            resp.sendError(404);
            return;
        }

        File file = FileHelper.getFile(upload.getGeneratedName());
        if (!file.exists()) {
            resp.sendError(404);
            return;
        }

        resp.setContentType(upload.getMimeType());
        resp.setContentLengthLong(file.length());
        try (InputStream in = new FileInputStream(file);
             OutputStream out = resp.getOutputStream()) {
            in.transferTo(out);
        }
    }
}
