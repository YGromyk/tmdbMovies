package com.gromyk.playground.ui.movies

import androidx.lifecycle.MutableLiveData
import com.gromyk.persistence.AppRepository
import com.gromyk.playground.App
import com.gromyk.playground.R
import com.gromyk.playground.api.ApiFactory
import com.gromyk.playground.api.dtos.genres.GenreDTO
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.repositories.GenreRepository
import com.gromyk.playground.repositories.MovieRepository
import com.gromyk.playground.ui.base.BaseViewModel
import com.gromyk.playground.utils.converters.toDBMovie
import com.gromyk.playground.utils.networkstate.onError
import com.gromyk.playground.utils.networkstate.onLoading
import com.gromyk.playground.utils.networkstate.onSuccess
import kotlinx.coroutines.launch
import org.koin.standalone.inject
import retrofit2.HttpException

class TmdbViewModel : BaseViewModel() {
    private val repository: MovieRepository =
        MovieRepository(ApiFactory.tmdbApi)
    val popularMoviesLiveData = MutableLiveData<MutableList<MovieDTO>>()
    val genresLiveData = MutableLiveData<List<GenreDTO>>()

    private val appRepository: AppRepository by inject()
    private val genresRepository: GenreRepository by inject()

    fun fetchData() {
        fetchMovies()
        fetchGenres()
    }

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
                                appRepository.genres.value
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
                appRepository.insertMovies(list?.map { it.toDBMovie() }
                    ?: emptyList())
            } catch (exception: HttpException) {
                networkState.onError(exception)
            }
        }
    }

    fun fetchGenres() {
        scope.launch {
            try {
                val genres = genresRepository.loadGenres()
                genresLiveData.postValue(genres)
            } catch (exception: HttpException) {
                networkState.onError(exception)
            }
        }

    }

    override fun updateTitle() {
        titleData.postValue(App.instance.getResourceProvider().getString(R.string.movies))
    }
}