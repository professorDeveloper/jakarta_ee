package org.azamov.learnjakarta.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/create")
public class UserCreateServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", req.getContextPath() + "/users/create");
        req.setAttribute("user", User.builder().build());
        req.getRequestDispatcher("/WEB-INF/views/users/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var user = User.builder()
                .username(req.getParameter("username"))
                .build();
        userService.create(user);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
