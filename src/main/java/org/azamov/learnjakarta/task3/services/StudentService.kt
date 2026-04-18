package org.azamov.learnjakarta.task3.services

import org.azamov.learnjakarta.task3.helper.getInfoByAnyTable
import org.azamov.learnjakarta.task3.model.Student
import org.postgresql.Driver
import java.sql.Connection
import java.sql.DriverManager

class StudentService {
    init {
        DriverManager.registerDriver(Driver())
    }

    @Throws(Exception::class)
    private fun getConnection(): Connection {
        return DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255"
        )
    }


    fun deleteStudentById(id: Int) {
        getConnection().use { connection ->
            val stmt = connection.prepareStatement("DELETE FROM students WHERE id = ?")
            stmt.setInt(1, id)
            stmt.executeUpdate()
        }
    }

    fun getStudents(): List<Student> {
        getConnection().use { connection ->
            val rs = connection.createStatement().executeQuery("SELECT * FROM students")
            val students = mutableListOf<Student>()
            while (rs.next()) {
                students.add(
                    Student(
                        id = rs.getInt("id"),
                        fullName = rs.getString("full_name"),
                        age = rs.getInt("age"),
                        groupId = rs.getInt("group_id"),
                        createdBy = rs.getInt("created_by"),
                    )
                )
            }
            return students
        }
    }

    fun createStudent(student: Student): Boolean {
        try {
            val stat =
                getConnection().prepareStatement("insert into students (full_name,age,group_id,created_by) values (?,?,?,?) returning *")
            stat.setString(1, student.fullName)
            stat.setInt(2, student.age)
            stat.setInt(3, student.groupId)
            stat.setInt(4, student.createdBy)
            return stat.execute()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }
}

