package org.azamov.learnjakarta.task7_1.servlet;

import org.azamov.learnjakarta.task7_1.services.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.azamov.learnjakarta.task7_1.helper.CookieManager.getUserIdByCookie;

@WebServlet(urlPatterns =
        {"/groups",""})
public class GroupServlet extends HttpServlet {
    private final GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("groups", groupService.getGroups());
        req.getRequestDispatcher("/WEB-INF/lms/groups.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String method = req.getParameter("_method");

        if ("DELETE".equals(method)) {
            int id = Integer.parseInt(req.getParameter("id"));
            groupService.deleteGroupById(id);
        } else {
            groupService.createGroup(req.getParameter("name"), getUserIdByCookie(req));
        }
        resp.sendRedirect(req.getContextPath() + "/groups");
    }

}