package org.azamov.learnjakarta.task2.java.servlet;

import org.azamov.learnjakarta.task2.java.model.User;
import org.azamov.learnjakarta.task2.java.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String fullName = req.getParameter("name");
        User registered = authService.register(User.builder().name(fullName).username(username).password(password).build());
        if (registered != null) {
            HttpSession session = req.getSession(false);
            session.setAttribute("username", registered.getUsername());
            resp.sendRedirect("/dashboard");
        }
    }
}
