package com.gromyk.playground.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gromyk.playground.MovieRepository
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.dtos.TmdbMovie
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TmdbViewModel : ViewModel() {
    private val parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default
    private val scope = CoroutineScope(coroutineContext)

    private val repository: MovieRepository = MovieRepository(ApiFactory.tmdbApi)

    val popularMoviesLiveData = MutableLiveData<MutableList<TmdbMovie>>()

    fun fetchMovies() {
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            popularMoviesLiveData.postValue(popularMovies)
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }
}