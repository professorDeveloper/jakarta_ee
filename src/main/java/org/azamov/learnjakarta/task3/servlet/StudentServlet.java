package org.azamov.learnjakarta.task3.servlet;

import org.azamov.learnjakarta.task3.model.Student;
import org.azamov.learnjakarta.task3.services.GroupService;
import org.azamov.learnjakarta.task3.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("students")
public class StudentServlet extends HttpServlet {
    private final StudentService studentService = new StudentService();
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", studentService.getStudents());
        req.setAttribute("groups", groupService.getGroups());
        req.getRequestDispatcher("/WEB-INF/lms/students.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getParameter("_method");
        if ("DELETE".equals(method)) {
            studentService.deleteStudentById(Integer.parseInt(req.getParameter("id")));
        } else {
            int userId = (int) req.getSession().getAttribute("userId");
            studentService.createStudent(new Student(
                -1,
                req.getParameter("fullName"),
                Integer.parseInt(req.getParameter("age")),
                Integer.parseInt(req.getParameter("groupId")),
                userId
            ));
        }
        resp.sendRedirect(req.getContextPath() + "students");
    }
}
