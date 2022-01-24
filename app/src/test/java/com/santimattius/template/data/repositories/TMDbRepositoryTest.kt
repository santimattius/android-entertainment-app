package com.santimattius.template.data.repositories

import com.santimattius.template.data.datasources.RemoteDataSource
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class TMDbRepositoryTest {

    private lateinit var remoteDataSource: RemoteDataSource
    private lateinit var repository: TMDbRepository

    @Before
    fun setUp() {
        remoteDataSource = mockk()
        repository = TMDbRepository(remoteDataSource)
    }


    @Test
    fun `get popular movies`() = runBlockingTest {

        coEvery { remoteDataSource.getPopularMovies() } returns emptyList()

        val pictures = repository.getPopularMovies()

        assert(pictures.isEmpty())
        coVerify { remoteDataSource.getPopularMovies() }

    }
}