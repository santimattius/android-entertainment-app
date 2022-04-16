package com.santimattius.template.data.datasources.implementation

import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.models.TMDbMovie
import com.santimattius.template.domain.entities.Movie
import com.santimattius.moviedb.network.model.Movie as TheMovieDbMovie

internal class MovieDataSource(
    private val theMovieDbClient: TheMovieDbClient,
) : RemoteDataSource {

    override suspend fun getPopularMovies(): List<Movie> {
        return theMovieDbClient.getMoviePopular(page = SINGLE_PAGE)
            .fold(
                onSuccess = { result -> result.results.asMovies() },
                onFailure = { emptyList() }
            )
    }

    private fun List<TheMovieDbMovie>.asMovies(): List<Movie> {
        return this.map { it.asMovie() }
    }

    private fun TheMovieDbMovie.asMovie(): Movie {
        return TMDbMovie(
            id = this.id,
            overview = this.overview,
            title = this.title,
            posterPath = this.poster,
            backdropPath = this.backdropPath.orEmpty()
        )
    }

    companion object {
        private const val SINGLE_PAGE = 1

    }
}
