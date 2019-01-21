package com.gromyk.playground.repositories

import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.api.services.TmdbService
import retrofit2.HttpException
import timber.log.Timber

class MovieRepository(private val api: TmdbService) {
    suspend fun getPopularMovies(): MutableList<MovieDTO>? {
        var popularMovies: MutableList<MovieDTO>? = null
        val popularMovieRequest = api.getPopularMovie()
        try {
            val response = popularMovieRequest.await()
            if (response.isSuccessful) {
                val movieResponse = response.body()
                popularMovies = movieResponse?.results?.toMutableList()
            } else {
                Timber.e(response.errorBody().toString())
            }
        } catch (e: Exception) {
        }
        return popularMovies
    }

    suspend fun getMovieBy(id: Int): MovieDTO? {
        val movie: MovieDTO?
        val movieRequest = api.getMovieById(id)
        val response = movieRequest.await()
        if (response.isSuccessful) {
            movie = response.body()
        } else {
            throw HttpException(response)
        }
        return movie
    }
}