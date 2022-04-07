package com.santimattius.template.domain.usecases

import com.santimattius.template.domain.repositories.MovieRepository
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPopularMoviesTest {

    private val repository: MovieRepository = mockk()
    private val useCase = GetPopularMovies(repository)

    @Test
    fun `invoke get popular movies use case`() {
        coEvery { repository.getPopularMovies() } returns emptyList()
        runBlockingTest {
            val result = useCase.invoke()
            assertThat(result, IsEqual(emptyList()))
        }
        coVerify { repository.getPopularMovies() }
    }
}