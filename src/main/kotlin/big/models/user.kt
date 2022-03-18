package my_kotlin_app.models

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.Table

object Profile : Table() {
    val id: Column<Int> = integer("id").autoIncrement()
    val email = varchar("email", 100)
    val password = varchar("password", 100)
    override val primaryKey = PrimaryKey(id)
}

data class ProfileType(
    val id: Int,
    val email: String?,
    val password: String?
)