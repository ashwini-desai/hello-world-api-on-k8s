package ashwini.dev

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.http.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.request.*
import org.slf4j.event.Level

fun main() {
    val port = System.getenv("PORT")?.toIntOrNull() ?: 8080
    val environment = System.getenv("ENVIRONMENT") ?: "local"
    
    embeddedServer(Netty, port = port) {
        module(environment)
    }.start(wait = true)
}

fun Application.module(environment: String) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }

    routing {
        get("/hello-world/v1") {
            call.respondText("hello-world")
        }

        get("/health/liveness") {
            call.respondText("OK", status = HttpStatusCode.OK)
        }

        get("/health/readiness") {
            // Add any additional readiness checks here
            call.respondText("OK", status = HttpStatusCode.OK)
        }

        get("/health/startup") {
            call.respondText("OK", status = HttpStatusCode.OK)
        }
    }
}