package org.azamov.learnjakarta.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/users/edit")
public class UserEditServlet extends HttpServlet {
    private final UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        req.setAttribute("action", req.getContextPath() + "/users/edit");
        req.setAttribute("user", userService.findById(id));
        req.getRequestDispatcher("/WEB-INF/views/users/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var user = User.builder()
                .id(req.getParameter("id"))
                .username(req.getParameter("username"))
                .build();
        userService.update(user);
        resp.sendRedirect(req.getContextPath() + "/users");
    }
}
