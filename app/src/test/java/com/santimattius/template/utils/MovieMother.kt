package com.santimattius.template.utils

import com.santimattius.template.data.entities.MovieDto

object MovieMother {

    fun list() = (1..10).map { MovieDto(id = it) }
}