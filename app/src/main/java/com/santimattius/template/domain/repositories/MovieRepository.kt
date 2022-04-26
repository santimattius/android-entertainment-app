package com.santimattius.template.domain.repositories

import com.santimattius.template.data.entities.MovieDto
import com.santimattius.template.domain.entities.Movie
import kotlinx.coroutines.flow.Flow

interface MovieRepository {

    suspend fun getPopular(): Flow<List<Movie>>

    suspend fun fetchPopular(): Result<Boolean>
}