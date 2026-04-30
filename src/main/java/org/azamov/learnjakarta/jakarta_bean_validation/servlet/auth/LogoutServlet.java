package org.azamov.learnjakarta.jakarta_bean_validation.servlet.auth;

import lombok.val;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Logout", value = "/logout")
public class LogoutServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        for (Cookie cookie : req.getCookies()) {
            if (cookie.getName().equals("rememberMe")) {
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
        resp.sendRedirect(req.getContextPath() + "/login");
    }
}
