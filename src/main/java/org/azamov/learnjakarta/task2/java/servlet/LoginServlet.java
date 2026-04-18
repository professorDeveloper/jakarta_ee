package org.azamov.learnjakarta.task2.java.servlet;

import org.azamov.learnjakarta.task2.java.service.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean login = authService.login(username, password);
        if (login) {
            HttpSession session = req.getSession(false);
            session.setAttribute("username", username);
            resp.sendRedirect("/dashboard");
        } else {
            req.setAttribute("error", "Username yoki password noto'g'ri!");
            req.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(req, resp);
        }
    }
}
