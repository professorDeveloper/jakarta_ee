package org.azamov.learnjakarta.jakarta_bean_validation.servlet.auth;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.PasswordHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private final AuthUserDao dao = new AuthUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String next = req.getParameter("next");
        if (next != null && !next.isBlank()) {
            req.setAttribute("next", next);
        }
        req.getRequestDispatcher("/views/auth/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        boolean rememberMe = "true".equals(req.getParameter("rememberMe"));
        String password = req.getParameter("password");
        dao.findByEmail(email).ifPresentOrElse(authUser -> {
            if (!PasswordHelper.check(password, authUser.getPassword())) {
                req.setAttribute("error", "Invalid email or password");
                try {
                    req.getRequestDispatcher("/views/auth/login.jsp").forward(req, resp);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else if (!(authUser.getStatus() == AuthUser.Status.ACTIVE)) {
                req.setAttribute("error", "User is not active");
                try {
                    req.getRequestDispatcher("/views/auth/login.jsp").forward(req, resp);
                } catch (ServletException | IOException e) {
                    throw new RuntimeException(e);
                }
            } else {
                HttpSession session = req.getSession();
                session.setAttribute("email", authUser.getEmail());
                session.setAttribute("username", authUser.getUsername());
                session.setAttribute("role", authUser.getRole());
                session.setAttribute("id", authUser.getId());
                Cookie cookie =new Cookie("rememberMe",authUser.getId());
                if (rememberMe) {
                    cookie.setMaxAge(10*24*60*60);
                    cookie.setPath("/");
                    resp.addCookie(cookie);
                }

                try {
                    String next = req.getParameter("next");
                    String target = (next != null && !next.isBlank()) ? next : "/book/list";
                    resp.sendRedirect(req.getContextPath() + target);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, () -> {
            req.setAttribute("error", "Invalid email or password");
            try {
                req.getRequestDispatcher("/views/auth/login.jsp").forward(req, resp);
            } catch (ServletException | IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
