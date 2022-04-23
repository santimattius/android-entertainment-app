package com.santimattius.template.ui.home.models.mapping

import com.santimattius.template.domain.entities.Movie
import com.santimattius.template.ui.home.models.MovieUiModel

internal fun List<Movie>.asUiModels() = map { it.asUiModel() }

internal fun Movie.asUiModel() = MovieUiModel(
    id = this.id,
    title = this.title,
    imageUrl = this.posterPath,
)