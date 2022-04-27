package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.data.client.database.MovieDao
import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.entities.MovieEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext

class RoomDataSource(
    private val dao: MovieDao,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
) : LocalDataSource {

    override fun getAll(): Flow<List<MovieEntity>> = dao.getAll().flowOn(dispatcher)

    override suspend fun isEmpty() =
        runWithContext { dao.count() == 0 }
            .getOrDefault(defaultValue = false)

    override suspend fun save(movies: List<MovieEntity>): Result<Boolean> =
        runWithContext { dao.insertAll(*movies.toTypedArray()); true }

    override suspend fun find(id: Int) =
        runWithContext { findById(id) }.fold(onSuccess = {
            if (it == null) Result.failure(MovieNoExists())
            else Result.success(it)
        }, onFailure = { Result.failure(MovieNoExists()) })

    override suspend fun delete(movie: MovieEntity) = runWithContext { delete(movie); true }

    override suspend fun update(movie: MovieEntity) = runWithContext { update(movie); true }

    private suspend fun <R> runWithContext(block: suspend MovieDao.() -> R) =
        withContext(dispatcher) {
            dao.runCatching { block() }
        }
}