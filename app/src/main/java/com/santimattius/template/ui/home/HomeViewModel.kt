package com.santimattius.template.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.santimattius.template.domain.usecases.GetPopularMovies
import com.santimattius.template.ui.home.models.HomeState
import com.santimattius.template.ui.home.models.mapping.asUiModels
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class HomeViewModel(private val getPopularMovies: GetPopularMovies) : ViewModel() {

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
        _state.postValue(HomeState.Loading)
        viewModelScope.launch(exceptionHandler) {
            val popularMovies = getPopularMovies().asUiModels()
            _state.postValue(HomeState.Data(values = popularMovies))
        }
    }

    fun retry() {
        popularMovies()
    }
}