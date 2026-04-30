package org.azamov.learnjakarta.jakarta_bean_validation.servlet.auth;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ConfirmServlet", value = "/confirm")
public class ConfirmServlet extends HttpServlet {
    private final AuthUserDao dao = new AuthUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String token = req.getParameter("token");

        dao.findByToken(token).ifPresentOrElse(user -> {
            user.setStatus(AuthUser.Status.ACTIVE);
            user.setToken(null);
            dao.update(user);
            try {
                resp.sendRedirect(req.getContextPath() + "/login?msg=activated");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, () -> {
            try {
                resp.sendRedirect(req.getContextPath() + "/login?msg=invalid_token");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

