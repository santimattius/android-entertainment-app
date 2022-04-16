package com.santimattius.moviedb

import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import com.santimattius.moviedb.network.model.Tv

interface TheMovieDbClient {

    suspend fun getMoviePopular(page: Int): Result<Response<Movie>>

    suspend fun getTvPopular(page: Int): Result<Response<Tv>>
}