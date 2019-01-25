package com.gromyk.playground.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.repositories.AllDataRepository
import com.gromyk.playground.repositories.GenreRepository
import com.gromyk.playground.utils.converters.toDBGenre
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val genresRepository = GenreRepository(ApiFactory.genresApi)
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

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
            AllDataRepository.getInstance().insertGenres(list?.map { it.toDBGenre() } ?: return@launch)
        }
    }

    override fun onCleared() {
        coroutineContext.cancel()
        super.onCleared()
    }
}