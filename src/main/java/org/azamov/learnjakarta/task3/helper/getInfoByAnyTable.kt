package org.azamov.learnjakarta.task3.helper

 import java.sql.DriverManager

private fun getConnection() = DriverManager.getConnection(
    "jdbc:postgresql://localhost:5432/jakarta?currentSchema=jdbc_example", "postgres", "2255"
)

fun getInfoByAnyTable(name: String) {
    require(name.matches(Regex("^[a-zA-Z0-9_]+$"))) {
        "Noto'g'ri jadval nomi: $name"
    }
    getConnection().use { connection ->
        val rsp = connection.prepareStatement("SELECT * FROM $name").executeQuery()
        val metaData = rsp.metaData
        val columnCount = metaData.columnCount

        println("Ustunlar soni: $columnCount")
        println("Ustun nomlari:")
        for (i in 1..columnCount) {
            println("  ${i}. ${metaData.getColumnName(i)} (${metaData.getColumnTypeName(i)})")
        }

        println("\nMa'lumotlar:")
        while (rsp.next()) {
            for (i in 1..columnCount) {
                print("${metaData.getColumnName(i)}: ${rsp.getString(i)}  ")
            }
            println()
        }

    }
}