package com.gromyk.playground

import android.util.Log
import com.gromyk.playground.api.TmdbService
import com.gromyk.playground.api.dtos.TmdbMovie

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
                Log.d("FetchMovie", popularMovies.toString())
            } else {
                Log.d("FetchMovie", response.errorBody().toString())
            }
        } catch (e: Exception) {
        }

        return popularMovies
    }

}