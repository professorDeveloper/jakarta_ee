package org.azamov.learnjakarta.jakarta_bean_validation.filter;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter implements Filter {
    private AuthUserDao dao = new AuthUserDao();
    private static final List<String> OPEN_URLS = List.of(
            "/", "/login", "/register", "/confirm", "/logout"
    );

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        String path = request.getRequestURI().substring(request.getContextPath().length());

        if (OPEN_URLS.contains(path) || path.startsWith("/uploads/") || checkForRememberMe(request)) {
            chain.doFilter(req, res);
            return;
        }

        HttpSession session = request.getSession(false);
        String username = session != null ? (String) session.getAttribute("username") : null;

        if (username == null) {
            String query = request.getQueryString();
            String full = query != null ? path + "?" + query : path;
            response.sendRedirect(request.getContextPath() + "/login?next=" + URLEncoder.encode(full, StandardCharsets.UTF_8));
            return;
        }

        if (path.startsWith("/admin/")) {
            String role = (String) session.getAttribute("role");
            if (!"ADMIN".equals(role)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Ruxsat yo'q");
                return;
            }
        }

        chain.doFilter(req, res);
    }

    private boolean checkForRememberMe(HttpServletRequest request) {
        if (request.getSession().getAttribute("id") != null) return true;
        Cookie[] cookies = request.getCookies();
        if (cookies == null) return false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("rememberMe")) {
                AuthUser authUser = dao.findByID(cookie.getValue());
                if (authUser == null) return false;
                HttpSession session = request.getSession();
                session.setAttribute("email", authUser.getEmail());
                session.setAttribute("username", authUser.getUsername());
                session.setAttribute("role", authUser.getRole());
                session.setAttribute("id", authUser.getId());
                return true;
            }
        }
        return false;
    }
}
