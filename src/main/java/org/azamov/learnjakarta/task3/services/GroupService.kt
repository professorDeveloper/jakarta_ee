package org.azamov.learnjakarta.task3.services

import org.azamov.learnjakarta.task3.helper.toGroup
import org.azamov.learnjakarta.task3.helper.toStudent
import org.azamov.learnjakarta.task3.model.Group
import org.azamov.learnjakarta.task3.model.Student
import org.postgresql.Driver
import java.sql.Connection
import java.sql.DriverManager

class GroupService {
    init {
        DriverManager.registerDriver(Driver())
    }

    @Throws(Exception::class)
    private fun getConnection(): Connection {
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255"
        )
    }

    fun createGroup(name: String, createdBy: Int): Boolean {
        getConnection().use { connection ->
            val stmt = connection.prepareStatement("INSERT INTO groups(name, created_by) VALUES (?, ?)")
            stmt.setString(1, name)
            stmt.setInt(2, createdBy)
            return stmt.executeUpdate() > 0
        }
    }

    fun deleteGroupById(id: Int): Int {
        getConnection().use { connection ->
            val stmt = connection.prepareStatement("DELETE FROM groups WHERE id = ?")
            stmt.setInt(1, id)
            return stmt.executeUpdate()
        }
    }

    fun getStudentsByGroupId(groupId: Int): List<Student> {
        require(groupId > 0) { "groupId must be positive" }
        val connection = getConnection()
        val statement = connection.createStatement()
        val students = ArrayList<Student>()
        val resultSet = statement.executeQuery("SELECT * FROM students where group_id = '$groupId';")
        while (resultSet.next()) {
            students.add(
                resultSet.toStudent()
            )
        }
        return students
    }

    fun getGroups(): List<Group> {
        val connection = getConnection()
        val statement = connection.createStatement()
        val resultSet = statement.executeQuery("SELECT * FROM groups")
        val groups = ArrayList<Group>()
        while (resultSet.next()) {
            groups.add(
                resultSet.toGroup()
            )
        }
        return groups
    }
}