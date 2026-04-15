package org.azamov.learnjakarta.lms_system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/students/create")
public class StudentCreateServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", req.getContextPath() + "/students/create");
        req.setAttribute("student", Student.builder().build());
        req.setAttribute("groups", groupService.getAll());
        req.setAttribute("defaultCreatedAt", LocalDateTime.now().withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        req.getRequestDispatcher("/WEB-INF/views/students/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var student = Student.builder()
                .createdAt(req.getParameter("createdAt"))
                .fullName(req.getParameter("fullName"))
                .age(Integer.parseInt(req.getParameter("age")))
                .groupId(req.getParameter("groupId"))
                .build();
        studentService.save(student);
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
