package org.azamov.learnjakarta.task3.servlet

import org.azamov.learnjakarta.task3.model.User
import org.azamov.learnjakarta.task3.services.AuthService
import javax.servlet.annotation.WebServlet
import javax.servlet.http.Cookie
import javax.servlet.http.HttpServlet
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebServlet(urlPatterns = ["login", "register", "logout"])
class AuthServlet : HttpServlet() {
    private val authService = AuthService()

    override fun doGet(req: HttpServletRequest, resp: HttpServletResponse) {
        when (req.servletPath) {
            "logout" -> {
                req.session.invalidate()
                resp.sendRedirect(req.contextPath + "login")
            }

            "register" -> req.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(req, resp)
            else -> req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp)
        }
    }

    override fun doPost(req: HttpServletRequest, resp: HttpServletResponse) {
        if (req.servletPath == "login") {
            val userId = authService.login(req.getParameter("username"), req.getParameter("password"))
            if (userId != null) {
                val cookie = Cookie("userId", userId.toString())
                cookie.secure = true
                resp.addCookie(cookie)
                resp.sendRedirect(req.contextPath + "groups")
            } else {
                req.setAttribute("error", "Username yoki parol noto'g'ri")
                req.getRequestDispatcher("/WEB-INF/auth/login.jsp").forward(req, resp)
            }
        } else {
            val user = authService.register(
                User(
                    name = req.getParameter("name"),
                    username = req.getParameter("username"),
                    password = req.getParameter("password")
                )
            )
            if (user != null) {
                resp.addCookie(Cookie("userId", user.id.toString()))
                resp.sendRedirect(req.contextPath + "groups")
            } else {
                req.setAttribute("error", "Username allaqachon mavjud")
                req.getRequestDispatcher("/WEB-INF/auth/register.jsp").forward(req, resp)
            }
        }
    }
}
