package org.azamov.learnjakarta.jakarta_bean_validation.servlet;

import jakarta.validation.ConstraintViolation;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.CookieManager;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.ValidationFactory;
import org.azamov.learnjakarta.jakarta_bean_validation.model.Student;
import org.azamov.learnjakarta.jakarta_bean_validation.services.GroupService;
import org.azamov.learnjakarta.jakarta_bean_validation.services.StudentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");
        if ("DELETE".equals(method)) {
            studentService.deleteStudentById(Integer.parseInt(req.getParameter("id")));
        } else {
            Student student = Student.builder()
                    .groupId(Integer.parseInt(req.getParameter("groupId")))
                    .fullName(req.getParameter("fullName"))
                    .createdBy(CookieManager.getUserIdByCookie(req))
                    .age(Integer.parseInt(req.getParameter("age")))
                    .build();

            Set<ConstraintViolation<Student>> violations = ValidationFactory.validate(student);
            if (!violations.isEmpty()) {
                req.setAttribute("error", ValidationFactory.getErrors(violations));
                req.setAttribute("students", studentService.getStudents());
                req.setAttribute("groups", groupService.getGroups());
                req.getRequestDispatcher("/WEB-INF/lms/students.jsp").forward(req, resp);
                return;
            }

            studentService.createStudent(student);
        }
        resp.sendRedirect(req.getContextPath() + "/students");
    }
}
