package org.azamov.learnjakarta.task2.kt.filter

import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.annotation.WebFilter
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
@WebFilter("/*")
class SecurityFilter : Filter {
    private val publicPaths = listOf("/login", "/register")

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val req = request as HttpServletRequest
        val resp = response as HttpServletResponse

        val path = req.servletPath
        if (publicPaths.any { path.equals(it) }) {
            chain.doFilter(request, response)
            return
        }
        val session = req.getSession(false)
        val username = session?.getAttribute("username")

        if (username != null) {
            chain.doFilter(request, response)
        } else {
            resp.sendRedirect("/login")
        }
    }
}