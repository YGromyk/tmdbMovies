package com.gromyk.playground.ui.movie

import androidx.lifecycle.MutableLiveData
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.repositories.MovieRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.networkstate.onError
import com.gromyk.playground.utils.networkstate.onLoading
import com.gromyk.playground.utils.networkstate.onSuccess
import kotlinx.coroutines.launch
import org.koin.standalone.inject

class MovieViewModel : BaseViewModel() {
    private val repository: MovieRepository by inject()
    val movieData = MutableLiveData<MovieDTO>()

    private var title: String? = null

    fun fetchMovieBy(id: Int) {
        networkState.onLoading()
        scope.launch {
            try {
                val movie = repository.getMovieBy(id)
                movieData.postValue(movie)
                title = movie?.title
                updateTitle()
                networkState.onSuccess()
            } catch (exception: Exception) {
                networkState.onError(exception)
            }
        }
    }

    override fun updateTitle() {
        titleData.postValue(title)
    }
}