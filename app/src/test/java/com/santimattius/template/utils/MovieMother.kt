package com.santimattius.template.utils

import com.santimattius.moviedb.network.model.Movie

object MovieMother {

    fun create() = (1..10).map { Movie(id = it) }
}