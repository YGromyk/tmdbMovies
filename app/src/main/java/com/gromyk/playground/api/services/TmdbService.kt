package com.gromyk.playground.api.services

import com.gromyk.playground.api.dtos.movies.TmdbMovie
import com.gromyk.playground.api.dtos.movies.TmdbMovieResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TmdbService {
    @GET("movie/popular")
    fun getPopularMovie(): Deferred<Response<TmdbMovieResponse>>

    @GET("movie/{id}")
    fun getMovieById(@Path("id") id:Int): Deferred<Response<TmdbMovie>>
}