package com.santimattius.moviedb

class TheMovieDbClientFactory {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"
        fun create(key: String): TheMovieDbClient =
            TheMovieDbRetrofitClient(BASE_URL, key)
    }
}