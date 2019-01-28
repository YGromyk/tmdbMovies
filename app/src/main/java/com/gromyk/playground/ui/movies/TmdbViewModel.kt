package com.gromyk.playground.ui.movies

import androidx.lifecycle.MutableLiveData
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.repositories.AllDataRepository
import com.gromyk.playground.repositories.MovieRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.converters.toDBMovie
import com.gromyk.playground.utils.networkstate.onError
import com.gromyk.playground.utils.networkstate.onLoading
import com.gromyk.playground.utils.networkstate.onSuccess
import kotlinx.coroutines.*
import retrofit2.HttpException

class TmdbViewModel : BaseViewModel() {
    private val repository: MovieRepository =
        MovieRepository(ApiFactory.tmdbApi)
    val popularMoviesLiveData = MutableLiveData<MutableList<MovieDTO>>()
    fun fetchMovies() {
        networkState.onLoading()
        scope.launch {
            try {
                val popularMovies = repository.getPopularMovies()
                val list = popularMovies?.map { item ->
                    item.genresNames = {
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
            } catch (exception: HttpException) {
                networkState.onError(exception)
            }
        }
    }
}