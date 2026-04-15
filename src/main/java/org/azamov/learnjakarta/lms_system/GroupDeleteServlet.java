package org.azamov.learnjakarta.lms_system;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/groups/delete")
public class GroupDeleteServlet extends HttpServlet {
    private final GroupService groupService = new GroupService();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        groupService.delete(req.getParameter("id"));
        resp.sendRedirect(req.getContextPath() + "/groups");
    }
}
