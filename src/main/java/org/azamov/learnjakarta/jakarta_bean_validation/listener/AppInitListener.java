package org.azamov.learnjakarta.jakarta_bean_validation.listener;

import org.azamov.learnjakarta.jakarta_bean_validation.dao.AuthUserDao;
import org.azamov.learnjakarta.jakarta_bean_validation.entity.AuthUser;
import org.azamov.learnjakarta.jakarta_bean_validation.helper.PasswordHelper;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppInitListener implements ServletContextListener {
    private final AuthUserDao authUserDao = new AuthUserDao();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        if (authUserDao.findByEmail("admin@library.azamov.me").isEmpty()) {
            AuthUser admin = AuthUser.childMethodBuilder()
                    .email("admin@library.azamov.me")
                    .username("Admin2255")
                    .password(PasswordHelper.encode("Admin2255"))
                    .role("ADMIN")
                    .status(AuthUser.Status.ACTIVE)
                    .build();
            authUserDao.save(admin);
            System.out.println("=== Default admin created: admin@library.azamov.me / Admin2255 ===");
        }
    }
}
