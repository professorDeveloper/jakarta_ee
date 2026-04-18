package org.azamov.learnjakarta.task3.services

import org.azamov.learnjakarta.task3.model.User
import org.postgresql.Driver
import java.sql.DriverManager

class AuthService {
    init {
        DriverManager.registerDriver(Driver())
        getConnection().use { connection ->
            connection.prepareStatement(
                """
            CREATE TABLE IF NOT EXISTS users(
                id serial PRIMARY KEY,
                name VARCHAR(255) NOT NULL,
                username VARCHAR(16) NOT NULL UNIQUE ,
                password VARCHAR(16) NOT NULL
            )
            """.trimIndent()
            ).execute()
        }
    }

    private fun getConnection() = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255"
    )

    fun login(username: String, password: String): Int? {
        getConnection().use { connection ->
            val statement = connection.prepareStatement(
                "SELECT id FROM users WHERE username = ? AND password = ?"
            )
            statement.setString(1, username)
            statement.setString(2, password)
            val rs = statement.executeQuery()
            return if (rs.next()) rs.getInt("id") else null
        }
    }

    fun register(user: User): User? {
        try {
            getConnection().use { connection ->
                val statement = connection.prepareStatement(
                    "INSERT INTO users(name, username, password) VALUES (?, ?, ?) RETURNING *"
                )
                statement.setString(1, user.name)
                statement.setString(2, user.username)
                statement.setString(3, user.password)
                val rs = statement.executeQuery()
                if (rs.next()) {
                    return User(
                        rs.getInt("id"), rs.getString("name"), rs.getString("username"), rs.getString("password")
                    )
                }
                return null
            }
        } catch (e: Exception) {
            println(e.message)
            return null
        }
    }

    fun delete(id: Int) {
        getConnection()?.use { connection ->
            val statement = connection.prepareStatement(
                "DELETE FROM users WHERE id = ?"
            )
            statement.setInt(1, id)
            statement.execute()
        }
    }

    fun getUsers(): MutableList<User> {
        val users = mutableListOf<User>()
        getConnection().use { connection ->
            val statement = connection.prepareStatement(
                "SELECT * FROM users ORDER BY id DESC "
            )
            val resultSet = statement.executeQuery()
            while (resultSet.next()) {
                val id = resultSet.getInt(1)
                val name = resultSet.getString(2)
                val username = resultSet.getString(3)
                val password = resultSet.getString(4)
                users.add(User(id, name, username, password))
            }
        }
        return users
    }
}
/*

fun main() {
    val authService = AuthService()
    val register = authService.register(
        User(
            name = "John Smith", username = "user", password = "password"
        )
    )
    println(register?:"")
    authService.getUsers().forEach {
        println(it.name + ": " + it.username + ": " + it.id)
    }
}*/
