package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.domain.entities.Movie
import com.santimattius.template.domain.repositories.MovieRepository
import com.santimattius.template.data.entities.MovieDto as TheMovieDbMovie

internal class TMDbRepository(
    private val remoteDataSource: RemoteDataSource,
) : MovieRepository {

    override suspend fun getPopularMovies(): List<Movie> {
        return remoteDataSource.getPopularMovies().fold(onSuccess = {
            it.asMovies()
        }, onFailure = {
            emptyList()
        })
    }

}

internal fun List<TheMovieDbMovie>.asMovies(): List<Movie> {
    return map { item -> item.asMovie() }
}


private fun TheMovieDbMovie.asMovie(): Movie {
    return Movie(
        id = this.id,
        overview = this.overview,
        title = this.title,
        posterPath = this.poster,
        backdropPath = this.backdropPath.orEmpty()
    )
}