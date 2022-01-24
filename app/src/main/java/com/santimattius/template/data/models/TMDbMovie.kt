package com.santimattius.template.data.models

import com.santimattius.template.domain.entities.Movie

data class TMDbMovie(
    override val id: Int,
    override val overview: String,
    override val title: String,
    override val posterPath: String,
    override val backdropPath: String
) : Movie