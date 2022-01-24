package com.santimattius.template.data.datasources.implementation

import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.moviedb.network.model.Movie
import com.santimattius.moviedb.network.model.Response
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test


@ExperimentalCoroutinesApi
class MovieDataSourceTest {

    private lateinit var movieDataSource: MovieDataSource

    private lateinit var client: TheMovieDbClient

    @Before
    fun setUp() {
        client = mockk()
        movieDataSource = MovieDataSource(client)
    }

    @Test
    fun `get populars movie on client result is success`() = runBlockingTest {

        val pictures = (1..10).map { Movie(id = it) }

        coEvery { client.getMoviePopular(any()) } returns Response<Movie>(results = pictures)

        val result = movieDataSource.getPopularMovies()

        Assert.assertEquals(pictures, result)

        coVerify { client.getMoviePopular(any()) }
    }

    @Test
    fun `get popular movie on client result is fail`() = runBlockingTest {

        coEvery { client.getMoviePopular(any()) } throws Throwable()

        val result = movieDataSource.getPopularMovies()

        assert(result.isEmpty())

        coVerify { client.getMoviePopular(any()) }
    }
}