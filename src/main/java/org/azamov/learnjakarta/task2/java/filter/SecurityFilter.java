package org.azamov.learnjakarta.task2.java.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    private final List<String> publicPaths = List.of("/login", "/register");

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        String path = req.getServletPath();
        if (publicPaths.contains(path)) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession(false);
        Object username = session != null ? session.getAttribute("username") : null;

        if (username != null) {
            chain.doFilter(request, response);
        } else {
            resp.sendRedirect("/login");
        }
    }
}
