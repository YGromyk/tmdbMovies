package com.gromyk.playground.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.dtos.movies.TmdbMovie
import com.gromyk.playground.repositories.AllDataRepository
import com.gromyk.playground.repositories.MovieRepository
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TmdbViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository =
            MovieRepository(ApiFactory.tmdbApi)

    val popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(
                    popularMovies?.map {
                        it.genres = {
                            val list = mutableListOf<String>()
                            it.genre_ids.forEach { id ->
                                list.add(
                                        AllDataRepository.getInstance().genres.value
                                                ?.find { it.id == id }?.name
                                                ?: return@forEach
                                )
                            }
                            list
                        }.invoke()
                        it
                    }?.toMutableList()
            )
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }
}