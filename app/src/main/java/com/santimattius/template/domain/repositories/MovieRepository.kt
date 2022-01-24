package com.santimattius.template.domain.repositories

import com.santimattius.template.domain.entities.Movie

interface MovieRepository {

    suspend fun getPopularMovies(): List<Movie>
}