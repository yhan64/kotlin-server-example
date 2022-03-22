package big.plugins

import big.utils.createJwtToken
import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import my_kotlin_app.service.ProfileService
import org.mindrot.jbcrypt.BCrypt

data class LoginRegister(val email: String, val password: String)
fun Application.configureRouting() {
    val profileService = ProfileService()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        post("/login") {
            val creds = call.receive<LoginRegister>()
            val profile = profileService.getProfileByEmail(creds.email)
            if (profile == null || !BCrypt.checkpw(creds.password, profile.password)) {
                error("Invalid Credentials")
            }
            val token = createJwtToken(profile.email)
            call.respond(hashMapOf("token" to token))
        }


        authenticate {
            get("/profiles") {
                val users = profileService.getAllUsers()
                call.respond(users)
            }
        }
    }
}
