package org.azamov.learnjakarta.jakarta_bean_validation.servlet;

import jakarta.validation.ConstraintViolation;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.ValidationFactory;
import org.azamov.learnjakarta.jakarta_bean_validation.model.Group;
import org.azamov.learnjakarta.jakarta_bean_validation.services.GroupService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

import static org.azamov.learnjakarta.jakarta_bean_validation.helper.CookieManager.getUserIdByCookie;

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
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("_method");

        if ("DELETE".equals(method)) {
            int id = Integer.parseInt(req.getParameter("id"));
            groupService.deleteGroupById(id);
        } else {
            Group group = Group.builder()
                    .name(req.getParameter("name"))
                    .createdBy(getUserIdByCookie(req))
                    .build();

            Set<ConstraintViolation<Group>> violations = ValidationFactory.validate(group);
            if (!violations.isEmpty()) {
                req.setAttribute("error", ValidationFactory.getErrors(violations));
                req.setAttribute("groups", groupService.getGroups());
                req.getRequestDispatcher("/WEB-INF/lms/groups.jsp").forward(req, resp);
                return;
            }

            groupService.createGroup(group.getName(), group.getCreatedBy());
        }
        resp.sendRedirect(req.getContextPath() + "/groups");
    }

}
