package com.santimattius.template.ui.viewmodels

import com.santimattius.template.ui.models.MovieUiModel

sealed class HomeState {
    object Loading : HomeState()
    object Error : HomeState()
    data class Data(val values: List<MovieUiModel>) : HomeState()
}
