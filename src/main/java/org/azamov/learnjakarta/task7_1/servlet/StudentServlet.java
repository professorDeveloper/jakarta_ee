package org.azamov.learnjakarta.task7_1.servlet;

import org.azamov.learnjakarta.task7_1.helper.CookieManager;
import org.azamov.learnjakarta.task7_1.model.Student;
import org.azamov.learnjakarta.task7_1.services.GroupService;
import org.azamov.learnjakarta.task7_1.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentServlet")
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

            studentService.createStudent(
                    Student.builder()
                            .groupId(Integer.parseInt(req.getParameter("group_id")))
                            .fullName(req.getParameter("full_name"))
                            .createdBy(CookieManager.getUserIdByCookie(req))
                            .age(Integer.parseInt(req.getParameter("age")))
                            .build()
            );
        }
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}