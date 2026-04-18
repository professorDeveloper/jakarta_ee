package org.azamov.learnjakarta.task2.java.session;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class AttributeListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {
    }
}
