package org.azamov.learnjakarta.jakarta_bean_validation.helper;

import jakarta.validation.constraints.NotNull;
import org.mindrot.jbcrypt.BCrypt;

public class PasswordHelper {
    public static String encode(@NotNull String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(@NotNull String password, @NotNull String encodedPassword) {
        return BCrypt.checkpw(password, encodedPassword);
    }
}
