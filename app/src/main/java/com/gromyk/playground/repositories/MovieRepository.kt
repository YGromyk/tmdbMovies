package com.gromyk.playground.repositories

import com.gromyk.playground.api.dtos.movies.TmdbMovie
import com.gromyk.playground.api.services.TmdbService
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
            } else {
                Timber.e(response.errorBody().toString())
            }
        } catch (e: Exception) {
        }
        return popularMovies
    }
}