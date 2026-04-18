package org.azamov.learnjakarta.task3.filter

import javax.servlet.*
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@WebFilter(urlPatterns = ["groups", "students"])
class AuthFilter : Filter {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val resp = response as HttpServletResponse
        val userCookie = req.cookies.find { it.name == "userId" }
        if (userCookie == null || userCookie.value == null) {
            resp.sendRedirect(req.contextPath + "login")
        } else {
            chain.doFilter(request, response)
        }
    }
}
