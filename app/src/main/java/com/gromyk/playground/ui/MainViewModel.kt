package com.gromyk.playground.ui

import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.repositories.AllDataRepository
import com.gromyk.playground.repositories.GenreRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.converters.toDBGenre
import kotlinx.coroutines.*

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

class MainViewModel : BaseViewModel() {
    private val genresRepository = GenreRepository(ApiFactory.genresApi)

    init {
        scope.launch {
            val isEmpty = AllDataRepository.getInstance().genres.value?.isEmpty() ?: true
            if (isEmpty)
                loadGenres()
        }
    }

    private fun loadGenres() {
        scope.launch {
            val list = genresRepository.loadGenres()
            AllDataRepository
                .getInstance()
                .insertGenres(
                    genres = list?.map { it.toDBGenre() }
                        ?: return@launch
                )
        }
    }

    override fun updateTitle() = Unit
}