package com.santimattius.template.ui.home.viewmodels

import com.santimattius.template.domain.entities.Movie
import com.santimattius.template.domain.repositories.MovieRepository
import kotlinx.coroutines.flow.flowOf

class FakeMovieRepository(
    private val answers: () -> List<Movie>,
    private val fetch: Result<Boolean>,
) : MovieRepository {

    constructor(answers: () -> List<Movie>) : this(answers = answers, fetch = Result.success(true))

    constructor(fetch: Result<Boolean>) : this(answers = { emptyList() }, fetch = fetch)

    override suspend fun getPopular() = flowOf(answers())
    override suspend fun fetchPopular() = fetch
}