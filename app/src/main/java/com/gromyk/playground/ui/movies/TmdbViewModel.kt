package com.gromyk.playground.ui.movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.repositories.AllDataRepository
import com.gromyk.playground.repositories.MovieRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.converters.toDBMovie
import com.gromyk.playground.utils.networkstate.NetworkState
import com.gromyk.playground.utils.networkstate.onLoading
import com.gromyk.playground.utils.networkstate.onSuccess
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class TmdbViewModel : BaseViewModel() {
    private val repository: MovieRepository =
        MovieRepository(ApiFactory.tmdbApi)
    val popularMoviesLiveData = MutableLiveData<MutableList<MovieDTO>>()
    fun fetchMovies() {
        networkState.onLoading()
        scope.launch {
            val popularMovies = repository.getPopularMovies()
            val list = popularMovies?.map { item ->
                item.genres = {
                    val list = mutableListOf<String>()
                    item.genreIds?.forEach { id ->
                        list.add(
                            AllDataRepository.getInstance().genres.value
                                ?.find { it.id == id }?.name
                                ?: return@forEach
                        )
                    }
                    list
                }.invoke()
                item
            }?.toMutableList()
            popularMoviesLiveData.postValue(list)
            networkState.onSuccess()
            AllDataRepository.getInstance().insertMovies(list?.map { it.toDBMovie() }
                ?: emptyList())
        }
    }

    private fun cancelAllRequests() = coroutineContext.cancel()

    override fun onCleared() {
        cancelAllRequests()
        super.onCleared()
    }
}