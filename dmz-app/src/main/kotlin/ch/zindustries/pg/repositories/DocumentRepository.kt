package ch.zindustries.pg.repositories

import ch.zindustries.pg.PostgresSingleton
import ch.zindustries.pg.entities.Document
import ch.zindustries.pg.entities.DocumentsTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll
import java.util.*

class DocumentRepository : RepositoryBase<Document, Long> {

    override fun rowToModel(row: ResultRow) = Document(
        id = row[DocumentsTable.id],
        name = row[DocumentsTable.name],
        size = row[DocumentsTable.size],
    )

    override suspend fun findById(id: Long) = PostgresSingleton.query {

        val potentialDoc = DocumentsTable.select(where = { DocumentsTable.id eq id })
            .map(::rowToModel)
            .singleOrNull()

        Optional.ofNullable(potentialDoc)
    }

    suspend fun addDocument(document: Document) = PostgresSingleton.query {
        DocumentsTable.insert {
            it[name] = document.name
            it[size] = document.size
        }
    }

    suspend fun allDocuments(): List<Document> = PostgresSingleton.query {
        DocumentsTable.selectAll()
            .map(::rowToModel)
    }
}
