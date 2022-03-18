package big.plugins

import io.ktor.server.routing.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import my_kotlin_app.service.ProfileService

fun Application.configureRouting() {
    val userService = ProfileService()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }

        authenticate {
            get("/profiles") {
                val users = userService.getAllUsers()
                call.respond(users)
            }
        }
    }
}
