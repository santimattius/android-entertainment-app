package com.santimattius.moviedb.network.service

import com.santimattius.moviedb.TheMovieDbRetrofitClient
import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import com.santimattius.moviedb.network.model.Tv
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieDBService {

    @GET("/{version}/movie/popular")
    suspend fun getMoviePopular(
        @Path("version") version: Int = TheMovieDbRetrofitClient.DEFAULT_VERSION,
        @Query("page") page: Int
    ): Response<Movie>

    @GET("/{version}/tv/popular")
    suspend fun getTvPopular(
        @Path("version") version: Int = TheMovieDbRetrofitClient.DEFAULT_VERSION,
        @Query("page") page: Int
    ): Response<Tv>
}
