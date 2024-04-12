package ch.zindustries.pg.repositories

import org.jetbrains.exposed.sql.ResultRow
import java.util.*

interface RepositoryBase<Model, Identifier> {

    fun rowToModel(row: ResultRow): Model

    suspend fun findById(id: Identifier): Optional<Model>
}
