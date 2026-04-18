package org.azamov.learnjakarta.task2.kt.session

import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSessionAttributeListener
import javax.servlet.http.HttpSessionBindingEvent

@WebListener
class AttributeListener : HttpSessionAttributeListener {

    override fun attributeAdded(event: HttpSessionBindingEvent?) {
        super.attributeAdded(event)
    }

    override fun attributeRemoved(event: HttpSessionBindingEvent?) {
        super.attributeRemoved(event)
    }

    override fun attributeReplaced(event: HttpSessionBindingEvent?) {
        super.attributeReplaced(event)
    }
}