package com.santimattius.template.domain.usecases

import com.santimattius.template.domain.repositories.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPopularMoviesTest {

    private lateinit var repository: MovieRepository
    private lateinit var useCase: GetPopularMovies

    @Before
    fun setUp() {
        repository = mockk()
        useCase = GetPopularMovies(repository)

    }

    @Test
    fun `invoke get popular movies use case`() = runBlockingTest {

        coEvery { repository.getPopularMovies() } returns emptyList()

        val result = useCase.invoke()

        assert(result.isEmpty())

        coVerify { repository.getPopularMovies() }

    }
}