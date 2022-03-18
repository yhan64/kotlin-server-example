package big

import io.ktor.server.application.*
import big.plugins.*
import my_kotlin_app.DatabaseFactory

fun main(args: Array<String>): Unit =
    io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // application.conf references the main function. This annotation prevents the IDE from marking it as unused.
fun Application.module() {
    configureSerialization()
    configureSecurity()
    DatabaseFactory.init()
    configureRouting()
}
