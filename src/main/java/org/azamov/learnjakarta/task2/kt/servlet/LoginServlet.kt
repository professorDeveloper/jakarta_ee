package org.azamov.learnjakarta.task2.kt.servlet

import org.azamov.learnjakarta.task2.kt.service.AuthService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(name = "LoginServlet", value = ["/login"])
class LoginServlet : HttpServlet() {
    val authService = AuthService()
    override fun doGet(req: HttpServletRequest?, resp: HttpServletResponse?) {
        req?.getRequestDispatcher("WEB-INF/auth/login.jsp")?.forward(req, resp) ?: return
    }

    override fun doPost(req: HttpServletRequest?, resp: HttpServletResponse?) {
        val username = req?.getParameter("username") ?: return
        val password = req.getParameter("password")
        val login = authService.login(username, password)
        if (login) {
            val session = req.getSession(false)
            session.setAttribute("username", username)
            resp?.sendRedirect("/dashboard")
        } else {
            req.setAttribute("error", "Username yoki password noto'g'ri!")
            req.getRequestDispatcher("WEB-INF/auth/login.jsp").forward(req, resp)

        }
    }
}