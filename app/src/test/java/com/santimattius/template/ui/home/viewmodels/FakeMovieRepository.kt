package com.santimattius.template.ui.home.viewmodels

import com.santimattius.template.domain.entities.Movie
import com.santimattius.template.domain.repositories.MovieRepository

class FakeMovieRepository(private val answers: () -> List<Movie>) : MovieRepository {
    override suspend fun getPopularMovies() = answers()
}