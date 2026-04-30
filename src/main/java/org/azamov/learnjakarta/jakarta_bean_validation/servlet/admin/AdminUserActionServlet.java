package org.azamov.learnjakarta.jakarta_bean_validation.servlet.admin;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/users/action")
public class AdminUserActionServlet extends HttpServlet {
    private final AuthUserDao authUserDao = new AuthUserDao();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String action = req.getParameter("action");

        if (id == null || action == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/users");
            return;
        }

        String currentUserId = (String) req.getSession().getAttribute("id");
        if (id.equals(currentUserId)) {
            resp.sendRedirect(req.getContextPath() + "/admin/users");
            return;
        }

        AuthUser user = authUserDao.findByID(id);
        if (user == null) {
            resp.sendRedirect(req.getContextPath() + "/admin/users");
            return;
        }

        switch (action) {
            case "block" -> {
                user.setStatus(AuthUser.Status.BLOCKED);
                authUserDao.update(user);
            }
            case "unblock" -> {
                user.setStatus(AuthUser.Status.ACTIVE);
                authUserDao.update(user);
            }
            case "delete" -> authUserDao.deleteById(id);
        }

        resp.sendRedirect(req.getContextPath() + "/admin/users");
    }
}
