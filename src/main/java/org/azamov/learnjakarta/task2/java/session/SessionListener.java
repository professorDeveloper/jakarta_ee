package org.azamov.learnjakarta.task2.java.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class SessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        String id = se.getSession().getId();
        System.out.println("created. session id = " + id);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
    }
}
