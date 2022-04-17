package com.santimattius.moviedb

import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import com.santimattius.moviedb.network.model.Tv
import com.santimattius.moviedb.network.service.TheMovieDBService

internal class TheMovieDbRetrofitClient(
    private val service: TheMovieDBService,
) : TheMovieDbClient {

    companion object {
        const val DEFAULT_VERSION = 3
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getTvPopular(page: Int): Result<Response<Tv>> {
        return try {
            val response = service.getTvPopular(version = DEFAULT_VERSION, page = page)
            Result.success(response)
        } catch (ex: Throwable) {
            Result.failure(ex)
        }
    }

    @Suppress("TooGenericExceptionCaught")
    override suspend fun getMoviePopular(page: Int): Result<Response<Movie>> {
        return try {
            val response = service.getMoviePopular(version = DEFAULT_VERSION, page = page)
            Result.success(response)
        } catch (ex: Throwable) {
            Result.failure(ex)
        }
    }
}