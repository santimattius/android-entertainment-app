package com.santimattius.template.presentation.viewmodels

import com.santimattius.template.presentation.models.MovieUiModel

sealed class HomeState {
    object Loading : HomeState()
    object Error : HomeState()
    data class Data(val values: List<MovieUiModel>) : HomeState()
}
