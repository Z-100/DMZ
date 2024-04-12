package ch.zindustries.pg.entities

import kotlinx.serialization.Serializable
import org.jetbrains.exposed.sql.Table

@Serializable
data class Document(
    val id: Long?,
    val name: String,
    val size: Int,
)

object DocumentsTable : Table() {
    val id = long("id").autoIncrement()
    val name = varchar("name", 255)
    val size = integer("size")

    override val primaryKey = PrimaryKey(id)
}
