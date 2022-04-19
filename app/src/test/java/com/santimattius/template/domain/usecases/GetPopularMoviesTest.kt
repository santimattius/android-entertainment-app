package com.santimattius.template.domain.usecases

import com.santimattius.template.ui.home.viewmodels.FakeMovieRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPopularMoviesTest {

    @Test
    fun `invoke get popular movies use case`() {
        val repository = FakeMovieRepository { emptyList() }
        val useCase = GetPopularMovies(repository)
        runBlockingTest {
            val result = useCase.invoke()
            assertThat(result, IsEqual(emptyList()))
        }
    }
}