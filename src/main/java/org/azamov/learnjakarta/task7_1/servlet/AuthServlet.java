package org.azamov.learnjakarta.task7_1.servlet;

import org.azamov.learnjakarta.task7_1.model.User;
import org.azamov.learnjakarta.task7_1.services.AuthService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/logout","/login","/register"})
public class AuthServlet extends HttpServlet {
    private final AuthService authService = new AuthService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = req.getServletPath();

        switch (path) {
            case "/logout":
                req.getSession().invalidate();
                Cookie[] cookies = req.getCookies();
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        cookie.setMaxAge(0);
                        resp.addCookie(cookie);
                    }
                }
                resp.sendRedirect(req.getContextPath() + "/login");
                break;
            case "/register":
                req.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(req, resp);
                break;
            default:
                req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if ("/login".equals(req.getServletPath())) {
            Integer userId = authService.login(req.getParameter("username"), req.getParameter("password"));
            if (userId != null) {
                Cookie cookie = new Cookie("userId", userId.toString());
                cookie.setPath("/");
                resp.addCookie(cookie);
                resp.sendRedirect(req.getContextPath() + "/groups");
            } else {
                req.setAttribute("error", "Username yoki parol noto'g'ri");
                req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp);
            }
        } else {
            User user = authService.register(User.builder().name(req.getParameter("name")).username(req.getParameter("username")).password(req.getParameter("password")).build());

            if (user != null) {
                Cookie cookie = new Cookie("userId", String.valueOf(user.getId()));
                cookie.setPath("/");
                resp.addCookie(cookie);
                resp.sendRedirect(req.getContextPath() + "/groups");
            } else {
                req.setAttribute("error", "Username allaqachon mavjud");
                req.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(req, resp);
            }
        }
    }
}