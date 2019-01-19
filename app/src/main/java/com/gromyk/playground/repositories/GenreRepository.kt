package com.gromyk.playground.repositories

import com.gromyk.playground.api.dtos.genres.GenreDTO
import com.gromyk.playground.api.services.GenreService
import timber.log.Timber

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

class GenreRepository(private val api: GenreService) {
    suspend fun loadGenres(): List<GenreDTO>? {
        var genres: List<GenreDTO>? = null
        val genresRequest = api.loadAllGenres()
        try {
            val response = genresRequest.await()
            if (response.isSuccessful) {
                val genresResponse = response.body()
                genres = genresResponse?.genres?.toMutableList()
                Timber.d(genres.toString())
            } else {
                Timber.e(response.errorBody().toString())
            }
        } catch (e: Exception) {
        }

        return genres
    }
}
