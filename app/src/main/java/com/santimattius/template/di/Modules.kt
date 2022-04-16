package com.santimattius.template.di

import com.santimattius.moviedb.TheMovieDbClient
import com.santimattius.moviedb.TheMovieDbClientFactory
import com.santimattius.template.BuildConfig
import com.santimattius.template.data.datasources.RemoteDataSource
import com.santimattius.template.data.datasources.implementation.MovieDataSource
import com.santimattius.template.data.repositories.TMDbRepository
import com.santimattius.template.domain.repositories.MovieRepository
import com.santimattius.template.domain.usecases.GetPopularMovies
import com.santimattius.template.ui.viewmodels.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * presentation layer definition module
 */
private val presentationModule = module {
    viewModel { HomeViewModel(getPopularMovies = get<GetPopularMovies>()) }
}


/**
 * domain layer definition module
 */
private val domainModule = module {
    factory { GetPopularMovies(repository = get<MovieRepository>()) }
}

/**
 * data layer definition module
 */
private val dataModule = module {

    factory<RemoteDataSource> { MovieDataSource(theMovieDbClient = get<TheMovieDbClient>()) }

    factory<MovieRepository> {
        TMDbRepository(
            remoteDataSource = get<RemoteDataSource>()
        )
    }

}

private const val API_KEY_NAMED = "api_key"

private val theMovieDBModule = module {
    single(named(API_KEY_NAMED)) { BuildConfig.API_KEY }
    single<TheMovieDbClient> {
        TheMovieDbClientFactory.create(
            get(named(API_KEY_NAMED))
        )
    }
}

internal val modules = listOf(presentationModule, domainModule, dataModule, theMovieDBModule)
