package com.gromyk.playground.api.services

import com.gromyk.playground.api.dtos.genres.GenresDTO
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Yurii Gromyk on 1/19/19.
 */

interface GenreService {
    @GET("genre/movie/list")
    fun loadAllGenres(): Deferred<Response<GenresDTO>>
}