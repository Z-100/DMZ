package ch.zindustries.plugins

import ch.zindustries.pg.entities.Document
import ch.zindustries.pg.repositories.DocumentRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val documentRepository = DocumentRepository()

fun Application.configurePostgresRouting() {
    routing {
        get("/postgres") {
            call.respondText("Hello Postgres!")
        }

        get("/postgres/documents") {
            call.respondText(Json.encodeToString(documentRepository.allDocuments()))
        }

        post("/postgres/documents") {

            val doc = Document(null, "Hello Postgres!", 5)

            documentRepository.addDocument(doc)

            call.respondText(Json.encodeToString(documentRepository.findById(1)))
        }
    }
}

fun Application.configureSolrRouting() {
    routing {
        get("/solr") {
            call.respondText("Hello Solr!")
        }
    }
}
