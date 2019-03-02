/**
 * Created by Yurii Gromyk
 * @date 3/2/19 5:37 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.di

import com.gromyk.playground.ui.MainViewModel
import com.gromyk.playground.ui.movie.MovieViewModel
import com.gromyk.playground.ui.movies.TmdbViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val viewModels = module {
    viewModel { MovieViewModel() }
    viewModel { MainViewModel() }
    viewModel { TmdbViewModel() }
}