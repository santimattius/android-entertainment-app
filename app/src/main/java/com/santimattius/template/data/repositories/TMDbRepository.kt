package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.domain.entities.Movie
import com.santimattius.template.domain.repositories.MovieRepository

internal class TMDbRepository(
    private val remoteDataSource: RemoteDataSource
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getPopularMovies()
    }

}