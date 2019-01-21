package com.gromyk.playground.ui.movie

import androidx.lifecycle.MutableLiveData
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.repositories.MovieRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.networkstate.onError
import com.gromyk.playground.utils.networkstate.onLoading
import com.gromyk.playground.utils.networkstate.onSuccess
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import timber.log.Timber
import java.lang.Exception

class MovieViewModel : BaseViewModel() {
    private val repository: MovieRepository =
        MovieRepository(ApiFactory.tmdbApi)
    val movieData = MutableLiveData<MovieDTO>()

    fun fetchMovieBy(id: Int) {
        networkState.onLoading()
        scope.launch {
            try {
                val movie = repository.getMovieBy(id)
                movieData.postValue(movie)
                networkState.onSuccess()
            } catch (exception: Exception) {
                networkState.onError(exception)
            }
        }
    }
}