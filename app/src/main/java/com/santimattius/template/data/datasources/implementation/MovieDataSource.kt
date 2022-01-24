package com.santimattius.template.data.datasources.implementation

import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.models.TMDbMovie
import com.santimattius.template.domain.entities.Movie
import com.santimattius.moviedb.network.model.Movie as TheMovieDbMovie

internal class MovieDataSource(
    private val theMovieDbClient: TheMovieDbClient
) : RemoteDataSource {

    override suspend fun getPopularMovies(): List<Movie> {
        return try {
            theMovieDbClient.getMoviePopular(page = SINGLE_PAGE).results.asMovies()
        } catch (ex: Throwable) {
            emptyList<Movie>()
        }
    }

    private fun List<TheMovieDbMovie>.asMovies(): List<Movie> {
        return this.map { it.asMovie() }
    }

    private fun TheMovieDbMovie.asMovie(): Movie {
        return TMDbMovie(
            id = this.id,
            overview = this.overview,
            title = this.title,
            posterPath = "${BASE_IMAGE_URL}${this@asMovie.posterPath}",
            backdropPath = this.backdropPath.orEmpty()
        )
    }

    companion object {
        private const val SINGLE_PAGE = 1
        private const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}
