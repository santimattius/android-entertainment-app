package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.LocalDataSource
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.dtoToEntity
import com.santimattius.template.data.entityToDomain
import com.santimattius.template.domain.entities.Movie
import com.santimattius.template.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TMDbRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : MovieRepository {

    override suspend fun getPopular(): Flow<List<Movie>> = localDataSource.getAll().map {
        it.entityToDomain()
    }

    override suspend fun fetchPopular() = remoteDataSource.getPopularMovies().fold(onSuccess = {
        localDataSource.save(it.dtoToEntity())
        Result.success(true)
    }, onFailure = {
        Result.failure(RefreshMovieFailed())
    })
}
