package com.santimattius.template.data.datasources.implementation

import com.santimattius.template.data.entities.MovieEntity

object MovieEntityMother {
    fun movies() = (1..10).map {
        MovieEntity(
            id = it,
            title = "Title $it",
            overview = "Overview $it",
            poster = "Poster $it"
        )
    }

    fun movie() = movies().first()
}
