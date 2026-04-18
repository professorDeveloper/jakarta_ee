package org.azamov.learnjakarta.task2.kt.session

import javax.servlet.annotation.WebListener
import javax.servlet.http.HttpSession
import javax.servlet.http.HttpSessionEvent
import javax.servlet.http.HttpSessionListener

@WebListener
class SessionListener:HttpSessionListener {
    override fun sessionCreated(se: HttpSessionEvent?) {
        super.sessionCreated(se)
        val session =se?.session?:return
        val id =session.id
        println("created. session id = $id")
    }

    override fun sessionDestroyed(se: HttpSessionEvent?) {
        super.sessionDestroyed(se)
    }
}