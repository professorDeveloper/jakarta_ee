package org.azamov.learnjakarta.lms_system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/students/edit")
public class StudentEditServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        req.setAttribute("action", req.getContextPath() + "/students/edit");
        req.setAttribute("student", studentService.findById(id));
        req.setAttribute("groups", groupService.getAll());
        req.getRequestDispatcher("/WEB-INF/views/students/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var student = Student.builder()
                .id(req.getParameter("id"))
                .createdAt(req.getParameter("createdAt"))
                .fullName(req.getParameter("fullName"))
                .age(Integer.parseInt(req.getParameter("age")))
                .groupId(req.getParameter("groupId"))
                .build();
        studentService.update(student);
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
