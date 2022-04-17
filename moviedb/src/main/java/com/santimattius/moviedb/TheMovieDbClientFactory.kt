package com.santimattius.moviedb

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.santimattius.moviedb.interceptors.RequestInterceptor
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

class TheMovieDbClientFactory private constructor() {

    companion object {
        private const val BASE_URL = "https://api.themoviedb.org"

        fun create(key: String): TheMovieDbClient {
            require(key.isNotBlank()) { "API Key required" }

            val client = OkHttpClient().newBuilder()
                .addInterceptor(RequestInterceptor(key))
                .build()

            return create(BASE_URL, client)
        }

        internal fun create(
            baseUrl: String,
            client: OkHttpClient = OkHttpClient().newBuilder().build(),
        ): TheMovieDbRetrofitClient {

            val retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
                .init()
                .build()

            return TheMovieDbRetrofitClient(service = retrofit.create())
        }

    }
}

private fun Retrofit.Builder.init(): Retrofit.Builder {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val moshiConverterFactory = MoshiConverterFactory.create(moshi)
    return this.addConverterFactory(moshiConverterFactory)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
}


