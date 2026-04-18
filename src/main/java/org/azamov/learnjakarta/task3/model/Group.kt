package org.azamov.learnjakarta.task3.model

data class Group(
    val id: Int = -1,
    val name: String,
    val createdAt: String,
    val studentCount: Int,
    val createdBy: Int = -1,
)