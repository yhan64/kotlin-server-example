package my_kotlin_app.service

import my_kotlin_app.models.Profile
import my_kotlin_app.DatabaseFactory.dbQuery
import my_kotlin_app.models.ProfileType
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class ProfileService {
    suspend fun getAllUsers(): List<ProfileType> = dbQuery {
        Profile.selectAll().map { toProfileType(it) }
    }

    suspend fun getUserByEmail(email: String): ProfileType? = dbQuery {
        Profile.select {
            (Profile.email eq email)
        }.mapNotNull { toProfileType(it) }
            .singleOrNull()
    }

    private fun toProfileType(row: ResultRow): ProfileType =
        ProfileType(
            id = row[Profile.id],
            email = row[Profile.email],
            password = row[Profile.password]
        )
}