package big.plugins

import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.request.*
import my_kotlin_app.service.UserService

fun Application.configureRouting() {
    val userService = UserService()
    routing {
        get("/") {
            call.respondText("Hello World!")
        }
        get("/users") {
            val users = userService.getAllUsers()
            println("-------->" + users)
            call.respond(users)
        }

    }
}
