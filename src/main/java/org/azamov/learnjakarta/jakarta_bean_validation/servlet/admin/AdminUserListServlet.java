package org.azamov.learnjakarta.jakarta_bean_validation.servlet.admin;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/users")
public class AdminUserListServlet extends HttpServlet {
    private final AuthUserDao authUserDao = new AuthUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AuthUser> users = authUserDao.findAll();
        req.setAttribute("users", users);
        req.getRequestDispatcher("/views/admin/users.jsp").forward(req, resp);
    }
}
