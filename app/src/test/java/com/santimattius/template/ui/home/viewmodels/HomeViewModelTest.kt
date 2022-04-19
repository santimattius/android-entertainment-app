package com.santimattius.template.ui.home.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.santimattius.template.domain.usecases.GetPopularMovies
import com.santimattius.template.ui.home.HomeViewModel
import com.santimattius.template.ui.home.models.HomeState
import com.santimattius.template.utils.CoroutinesTestRule
import com.santimattius.template.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsEqual
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutinesTestRule = CoroutinesTestRule()

    @Test
    fun `check case when init view model`() {

        val userCase = GetPopularMovies(repository = FakeMovieRepository { emptyList() })

        val viewModel = HomeViewModel(userCase)

        assertThat(viewModel.state.getOrAwaitValue(), IsEqual(HomeState.Data(emptyList())))
    }

    @Test
    fun `check when init fail with exception`() {

        val userCase = GetPopularMovies(repository = FakeMovieRepository { throw TestException() })

        val viewModel = HomeViewModel(userCase)

        assertThat(viewModel.state.getOrAwaitValue(), IsEqual(HomeState.Error))
    }

    @Test
    fun `check case with retry`() {

        val userCase = GetPopularMovies(repository = FakeMovieRepository { emptyList() })

        val viewModel = HomeViewModel(userCase)

        viewModel.retry()

        assertThat(viewModel.state.getOrAwaitValue(), IsEqual(HomeState.Data(emptyList())))
    }

    class TestException : Throwable()
}