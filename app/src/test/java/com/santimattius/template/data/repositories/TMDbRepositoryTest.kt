package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.RemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

@ExperimentalCoroutinesApi
class TMDbRepositoryTest {

    private val remoteDataSource: RemoteDataSource = mockk()
    private val repository = TMDbRepository(remoteDataSource)

    @Test
    fun `get popular movies`() {

        coEvery { remoteDataSource.getPopularMovies() } returns emptyList()

        runBlockingTest {
            val pictures = repository.getPopularMovies()
            assertThat(pictures, IsEqual(emptyList()))
        }

        coVerify { remoteDataSource.getPopularMovies() }
    }
}