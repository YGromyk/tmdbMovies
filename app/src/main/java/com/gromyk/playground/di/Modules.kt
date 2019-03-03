/**
 * Created by Yurii Gromyk
 * @date 3/2/19 5:37 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.di

import com.gromyk.persistence.AppRepository
import com.gromyk.playground.App
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.services.GenreService
import com.gromyk.playground.api.services.TmdbService
import com.gromyk.playground.repositories.GenreRepository
import com.gromyk.playground.repositories.MovieRepository
import com.gromyk.playground.ui.MainViewModel
import com.gromyk.playground.ui.movie.MovieViewModel
import com.gromyk.playground.ui.movies.TmdbViewModel
import com.instabug.library.analytics.model.Api
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModels = module {
    viewModel { MovieViewModel() }
    viewModel { MainViewModel() }
    viewModel { TmdbViewModel() }
}

val api = module {
    single { ApiFactory.retrofit().create(TmdbService::class.java) }
    single { ApiFactory.retrofit().create(GenreService::class.java) }
}
val repositories = module {
    single { GenreRepository(api = ApiFactory.genresApi)}
    single { MovieRepository(api = ApiFactory.tmdbApi)}
    single { AppRepository(application = App.instance) }
}

