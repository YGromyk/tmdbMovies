package com.gromyk.playground.repositories

import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.api.services.TmdbService
import retrofit2.HttpException

class MovieRepository(private val api: TmdbService) {
    suspend fun getPopularMovies(): MutableList<MovieDTO>? {
        val popularMovies: MutableList<MovieDTO>?
        val popularMovieRequest = api.getPopularMovieAsync()
        val response = popularMovieRequest.await()
        if (response.isSuccessful) {
            val movieResponse = response.body()
            popularMovies = movieResponse?.results?.toMutableList()
        } else {
            throw HttpException(response)
        }
        return popularMovies
    }

    suspend fun getMovieBy(id: Int): MovieDTO? {
        val movie: MovieDTO?
        val movieRequest = api.getMovieByIdAsync(id)
        val response = movieRequest.await()
        if (response.isSuccessful) {
            movie = response.body()
        } else {
            throw HttpException(response)
        }
        return movie
    }
}