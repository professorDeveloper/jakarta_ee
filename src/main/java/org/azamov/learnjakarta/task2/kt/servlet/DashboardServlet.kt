package org.azamov.learnjakarta.task2.kt.servlet

import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "DashboardServlet", value = ["kt/dashboard", ""])
class DashboardServlet : HttpServlet() {
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req?.getRequestDispatcher("WEB-INF/dashboard.jsp")?.forward(req, resp)
    }
}