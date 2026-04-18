package org.azamov.learnjakarta.task2.kt.servlet

import org.azamov.learnjakarta.task2.kt.model.User
import org.azamov.learnjakarta.task2.kt.service.AuthService
import javax.servlet.Servlet
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "RegisterServlet", value = ["kt/register"])
class RegisterServlet : HttpServlet() {
    private val authService = AuthService()
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req?.getRequestDispatcher("WEB-INF/auth/register.jsp")?.forward(req, resp)
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val username = req?.getParameter("username") ?: return
        val password = req.getParameter("password")
        val fullName = req.getParameter("name")
        val register = authService.register(
            User(
                username = username,
                name = fullName,
                password = password
            )
        )
        register?.apply {
            val session = req.getSession(false)
            session.setAttribute("username", this.username)
            resp?.sendRedirect("/dashboard")
        }
    }

}