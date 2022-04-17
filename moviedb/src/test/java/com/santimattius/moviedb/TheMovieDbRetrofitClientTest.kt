package com.santimattius.moviedb


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.net.HttpURLConnection

@ExperimentalCoroutinesApi
class TheMovieDbRetrofitClientTest {

    private val jsonLoader = JsonLoader()
    private lateinit var mockWebServer: MockWebServer
    private lateinit var client: TheMovieDbClient

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        val baseUrl = mockWebServer.url("/").toUri().toString()
        client = TheMovieDbClientFactory.create(baseUrl = baseUrl)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun getTvPopularSuccess() {

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(jsonLoader.load("tv_popular_response_success"))

        mockWebServer.enqueue(response)

        runBlocking {
            val result = client.getTvPopular(page = 1)
            assertThat(result.isSuccess, equalTo(true))
            assertThat(result.getOrNull()?.results?.isNotEmpty(), equalTo(true))
        }
    }

    @Test
    fun getTvPopularFail() {

        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            .setBody(jsonLoader.load("the_movie_db_response_fail"))

        mockWebServer.enqueue(response)

        runBlocking {
            val result = client.getTvPopular(page = 1)
            assertThat(result.isFailure, equalTo(true))
        }
    }

    @Test
    fun getMoviePopularSuccess() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_OK)
            .setBody(jsonLoader.load("movie_popular_response_success"))

        mockWebServer.enqueue(response)

        runBlocking {
            val result = client.getMoviePopular(page = 1)
            assertThat(result.isSuccess, equalTo(true))
            assertThat(result.getOrNull()?.results?.isNotEmpty(), equalTo(true))
        }
    }

    @Test
    fun getMoviePopularFail() {
        val response = MockResponse()
            .setResponseCode(HttpURLConnection.HTTP_UNAUTHORIZED)
            .setBody(jsonLoader.load("the_movie_db_response_fail"))

        mockWebServer.enqueue(response)

        runBlocking {
            val result = client.getMoviePopular(page = 1)
            assertThat(result.isFailure, equalTo(true))
        }
    }
}