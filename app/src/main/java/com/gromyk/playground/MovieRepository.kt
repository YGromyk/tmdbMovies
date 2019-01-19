package com.gromyk.playground

import com.gromyk.playground.api.TmdbService
import com.gromyk.playground.api.dtos.TmdbMovie
import timber.log.Timber

class MovieRepository(private val api: TmdbService) {
    suspend fun getPopularMovies(): MutableList<TmdbMovie>? {
        var popularMovies: MutableList<TmdbMovie>? = null
        val popularMovieRequest = api.getPopularMovie()
        try {
            val response = popularMovieRequest.await()
            if (response.isSuccessful) {
                val movieResponse = response.body()
                //This is single object Tmdb Movie response
                popularMovies = movieResponse?.results?.toMutableList()
                Timber.d(popularMovies.toString())
            } else {
                Timber.e(response.errorBody().toString())
            }
        } catch (e: Exception) {
        }

        return popularMovies
    }

}