package org.azamov.learnjakarta.task3.helper

import org.azamov.learnjakarta.task3.model.Group
import org.azamov.learnjakarta.task3.model.Student
import java.sql.ResultSet

fun ResultSet.toStudent(): Student {
    return Student(
        id = getInt("id"),
        fullName = getString("full_name"),
        age = getInt("age"),
        groupId = getInt("group_id"),
        createdBy = getInt("created_by"),
    )
}

fun ResultSet.toGroup(): Group {
    return Group(
        id = getInt("id"),
        name = getString("name"),
        createdBy = getInt("created_by"),
        createdAt = getString("created_at"),
        studentCount = getInt("student_count"),
    )
}