package com.santimattius.template.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.template.domain.usecases.FetchPopularMovies
import com.santimattius.template.domain.usecases.GetPopularMovies
import com.santimattius.template.ui.home.models.HomeState
import com.santimattius.template.ui.home.models.mapping.asUiModels
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getPopularMovies: GetPopularMovies,
    private val fetchPopularMovies: FetchPopularMovies,
) : ViewModel() {

    private val _state = MutableLiveData<HomeState>()
    val state: LiveData<HomeState>
        get() = _state

    private val exceptionHandler = CoroutineExceptionHandler { _, _ ->
        _state.postValue(HomeState.Error)
    }

    init {
        popularMovies()
    }

    private fun popularMovies() {
        viewModelScope.launch(exceptionHandler) {
            getPopularMovies().collectLatest { popularMovies ->
                _state.postValue(HomeState.Data(values = popularMovies.asUiModels()))
            }
        }
    }

    fun fetch() {
        _state.postValue(HomeState.Loading)
        viewModelScope.launch(exceptionHandler) {
            fetchPopularMovies().onFailure {
                _state.postValue(HomeState.Error)
            }
        }
    }
}