package org.azamov.learnjakarta.task7_1

import java.sql.DriverManager

private fun getConnection() = DriverManager.getConnection(
    "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255"
)

fun main(args: Array<String>) {
    getInfoByAnyTable("orm_example")
}
fun getInfoByAnyTable(name: String) {
    require(name.matches(Regex("^[a-zA-Z0-9_]+$"))) {
        "u are looking like danger."
    }
    getConnection().use { connection ->
        val rsp = connection.prepareStatement("SELECT * FROM $name").executeQuery()
        val metaData = rsp.metaData
        val columnCount = metaData.columnCount

        println("Columns: $columnCount")
        println("Colum names:")
        for (i in 1..columnCount) {
            println("  ${i}. ${metaData.getColumnName(i)} (${metaData.getColumnTypeName(i)})")
        }

        println("info:")
        while (rsp.next()) {
            for (i in 1..columnCount) {
                print("${metaData.getColumnName(i)}: ${rsp.getString(i)}  ")
            }
            println()
        }

    }
}