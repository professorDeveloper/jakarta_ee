package org.azamov.learnjakarta.lms_system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students")
public class StudentListServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentService.getAll());
        req.setAttribute("groups", groupService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/students/list.jsp").forward(req, resp);
    }
}
