package com.santimattius.template.data.datasources

import com.santimattius.template.data.entities.MovieEntity
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    fun getAll(): Flow<List<MovieEntity>>

    suspend fun isEmpty(): Boolean

    suspend fun save(movies: List<MovieEntity>): Result<Boolean>

    suspend fun find(id: String): Result<MovieEntity>

    suspend fun delete(movie: MovieEntity): Result<Boolean>

    suspend fun update(movie: MovieEntity): Result<Boolean>

}