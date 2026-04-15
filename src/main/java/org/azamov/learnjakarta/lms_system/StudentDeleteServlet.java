package org.azamov.learnjakarta.lms_system;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students/delete")
public class StudentDeleteServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        studentService.delete(req.getParameter("id"));
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
