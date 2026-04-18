package org.azamov.learnjakarta.task3.servlet

import org.azamov.learnjakarta.task3.services.GroupService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "GroupServlet", value = ["groups"])
class GroupServlet : HttpServlet() {
    private val groupService = GroupService()
    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        req.setAttribute("groups", groupService.getGroups())
        req.getRequestDispatcher("/WEB-INF/lms/groups.jsp").forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        if (req.getParameter("_method") == "DELETE") {
            groupService.deleteGroupById(req.getParameter("id").toInt())
        } else {
            val userId = req.session.getAttribute("userId") as Int
            groupService.createGroup(req.getParameter("name"), userId)
        }
        resp.sendRedirect(req.contextPath + "groups")
    }
}
