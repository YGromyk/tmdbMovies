package com.gromyk.playground.api.dtos.movies

data class TmdbMovie(
    val id: Int,
    val vote_average: Double,  // serializes
    val title: String,         // serializes
    val overview: String,      // serializes
    val adult: Boolean,        // serializes
    val backdrop_path: String, // serializes
    var genre_ids: List<Int>,  // serializes
    var release_date: String, // serializes
    var genres: List<String> = mutableListOf()
)