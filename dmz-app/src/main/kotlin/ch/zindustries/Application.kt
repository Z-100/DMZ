package ch.zindustries

import ch.zindustries.pg.PostgresSingleton
import ch.zindustries.plugins.*
import io.ktor.server.application.*
import io.ktor.server.netty.*

fun main(args: Array<String>) = EngineMain.main(args)

fun Application.module() {
    configurePostgresRouting()
    configureSolrRouting()
    PostgresSingleton.initDatabase(environment.config)
}
