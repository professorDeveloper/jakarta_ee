package org.azamov.learnjakarta.task7_1.helper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

public class CookieManager {

    public static int getUserIdByCookie(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        int userId = 0;
        if (cookies != null) {
            userId = Arrays.stream(cookies)
                    .filter(c -> "userId".equals(c.getName()))
                    .map(Cookie::getValue)
                    .map(Integer::parseInt)
                    .findFirst()
                    .orElse(0);
        }
        return userId;
    }
}
