package org.azamov.learnjakarta.lms_system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/groups/edit")
public class GroupEditServlet extends HttpServlet {
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var id = req.getParameter("id");
        req.setAttribute("action", req.getContextPath() + "/groups/edit");
        req.setAttribute("group", groupService.findById(id));
        req.getRequestDispatcher("/WEB-INF/views/groups/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var group = Group.builder()
                .id(req.getParameter("id"))
                .name(req.getParameter("name"))
                .createdAt(req.getParameter("createdAt"))
                .studentCount(Integer.parseInt(req.getParameter("studentCount")))
                .build();
        groupService.update(group);
        resp.sendRedirect(req.getContextPath() + "/groups");
    }
}
