package org.azamov.learnjakarta.jakarta_bean_validation.servlet.auth;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.EmailHelper;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.PasswordHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@WebServlet(name = "RegisterServlet", value = "/register")
public class RegisterServlet extends HttpServlet {
    private final AuthUserDao dao = new AuthUserDao();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        Map<String, String> errors = new HashMap<>();

        if (email == null || email.isBlank())
            errors.put("email_err", "Email kiritish shart");
        else if (dao.findByEmail(email).isPresent())
            errors.put("email_err", "Bu email band");

        if (username == null || username.isBlank())
            errors.put("username_err", "Username kiritish shart");
        else if (dao.findByUsername(username).isPresent())
            errors.put("username_err", "Bu username band");

        if (password == null || password.isBlank())
            errors.put("password_err", "Parol kiritish shart");
        else if (password.length() < 6)
            errors.put("password_err", "Parol kamida 6 ta belgidan iborat bo'lishi kerak");
        if (!errors.isEmpty()) {
            errors.forEach(req::setAttribute);
            req.getRequestDispatcher("/views/auth/register.jsp").forward(req, resp);
            return;
        }
        String token = UUID.randomUUID().toString();

        AuthUser authUser = AuthUser.childMethodBuilder()
                .email(email)
                .role("USER")
                .username(username)
                .password(PasswordHelper.encode(password))
                .build();
        authUser.setToken(token);

        dao.save(authUser);
        EmailHelper.sendConfirmation(email, token);

        resp.sendRedirect(req.getContextPath() + "/login?msg=check_email");

    }
}
