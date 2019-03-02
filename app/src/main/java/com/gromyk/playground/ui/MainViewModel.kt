package com.gromyk.playground.ui

import com.gromyk.persistence.AppRepository
import com.gromyk.playground.repositories.GenreRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.converters.toDBGenre
import kotlinx.coroutines.launch
import org.koin.standalone.inject

/**
 * Created by Yurii Gromyk on 1/19/19.
 */

class MainViewModel : BaseViewModel() {
    private val genresRepository: GenreRepository by inject()
    private val appRepository: AppRepository by inject()

    init {
        scope.launch {
            val isEmpty = appRepository.genres.value?.isEmpty() ?: true
            if (isEmpty)
                loadGenres()
        }
    }

    private fun loadGenres() {
        scope.launch {
            val list = genresRepository.loadGenres()
            appRepository
                .insertGenres(
                    genres = list?.map { it.toDBGenre() }
                        ?: return@launch
                )
        }
    }

    override fun updateTitle() = Unit
}