package ch.zindustries.pg

import ch.zindustries.pg.entities.DocumentsTable
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object PostgresSingleton {

    fun initDatabase(config: ApplicationConfig) {
        val driverClassName = config.property("postgres.driverClassName").getString()
        val jdbcURL = config.property("postgres.jdbcURL").getString()
        val database = Database.connect(jdbcURL, driverClassName)

        transaction(database) {
            SchemaUtils.create(DocumentsTable)
        }
    }

    suspend fun <T> query(block: () -> T): T = newSuspendedTransaction(Dispatchers.IO) { block() }
}
