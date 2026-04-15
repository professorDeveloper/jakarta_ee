package org.azamov.learnjakarta.lms_system;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet("/groups/create")
public class GroupCreateServlet extends HttpServlet {
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("action", req.getContextPath() + "/groups/create");
        req.setAttribute("group", Group.builder().build());
        req.setAttribute("defaultCreatedAt", LocalDateTime.now().withSecond(0).withNano(0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")));
        req.getRequestDispatcher("/WEB-INF/views/groups/form.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        var group = Group.builder()
                .name(req.getParameter("name"))
                .createdAt(req.getParameter("createdAt"))
                .studentCount(Integer.parseInt(req.getParameter("studentCount")))
                .build();
        groupService.create(group);
        resp.sendRedirect(req.getContextPath() + "/groups");
    }
}
