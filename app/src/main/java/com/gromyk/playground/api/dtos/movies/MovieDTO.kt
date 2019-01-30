package com.gromyk.playground.api.dtos.movies

import com.gromyk.playground.api.dtos.genres.GenreDTO
import com.squareup.moshi.Json

data class MovieDTO(
        @field:Json(name = "overview")
        var overview: String = "",
        @field:Json(name = "original_language")
        var originalLanguage: String = "",
        @field:Json(name = "original_title")
        var originalTitle: String = "",
        @field:Json(name = "video")
        var video: Boolean = false,
        @field:Json(name = "title")
        var title: String = "",
        @field:Json(name = "genre_ids")
        var genreIds: List<Int>?,
        @field:Json(name = "poster_path")
        var posterPath: String = "",
        @field:Json(name = "backdrop_path")
        var backdropPath: String? = null,
        @field:Json(name = "release_date")
        var releaseDate: String = "",
        @field:Json(name = "vote_average")
        var voteAverage: Double = 0.0,
        @field:Json(name = "popularity")
        var popularity: Double = 0.0,
        @field:Json(name = "id")
        var id: Int = 0,
        @field:Json(name = "adult")
        var adult: Boolean = false,
        @field:Json(name = "vote_count")
        var voteCount: Int = 0,
        @field:Json(name = "genres")
        var genres: List<GenreDTO> = mutableListOf(),
        @field:Json(name = "tagline")
        var tagline: String? = null
) {
    var genresNames: List<String> = mutableListOf()
}