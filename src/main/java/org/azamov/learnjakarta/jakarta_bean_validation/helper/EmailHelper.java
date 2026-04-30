package org.azamov.learnjakarta.jakarta_bean_validation.helper;

import com.resend.Resend;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;

import static org.hibernate.query.sqm.tree.SqmNode.log;

public class EmailHelper {

    private static final String API_KEY = "re_fW2YZrHj_BXgxfTVdfXu9D26FHRQRnxzx";
    private static final String FROM = "noreply@email.azamov.me";

    public static void sendConfirmation(String toEmail, String token) {
        String link = "http://localhost:2451/confirm?token=" + token;

        Resend resend = new Resend(API_KEY);
        CreateEmailOptions params = CreateEmailOptions.builder()
                .from(FROM)
                .to(toEmail)
                .subject("Emailingizni tasdiqlang")
                .html("""
                            <div style="font-family:sans-serif;max-width:480px;margin:auto;padding:32px">
                              <h2 style="color:#1c1c1c">Hisobni faollashtiring</h2>
                              <p style="color:#666">Quyidagi tugmani bosib emailingizni tasdiqlang:</p>
                              <a href="%s"
                                 style="display:inline-block;margin-top:16px;padding:11px 24px;
                                        background:#1c1c1c;color:#fff;border-radius:8px;
                                        text-decoration:none;font-weight:600">
                                Tasdiqlash
                              </a>
                              <p style="margin-top:24px;color:#aaa;font-size:13px">
                                Agar siz ro'yxatdan o'tmagan bo'lsangiz, bu xatni e'tiborsiz qoldiring.
                              </p>
                            </div>
                        """.formatted(link))
                .build();

        try {
            resend.emails().send(params);
        } catch (ResendException e) {
            log.error(e.getMessage());
        }
    }
}

