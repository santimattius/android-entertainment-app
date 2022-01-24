package com.santimattius.template.data.datasources

import com.santimattius.template.domain.entities.Movie

interface RemoteDataSource {

    suspend fun getPopularMovies(): List<Movie>
}