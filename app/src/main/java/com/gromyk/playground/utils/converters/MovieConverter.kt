package com.gromyk.playground.utils.converters

import com.gromyk.persistence.model.genres.movie.DBMovie
import com.gromyk.playground.api.dtos.movies.MovieDTO

/**
 * Created by Yurii Gromyk on 1/20/19.
 */

fun MovieDTO.toDBMovie() = DBMovie(
    id = id,
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    video = video,
    title = title,
    genreIds = genreIds?.firstOrNull() ?: 0,
    posterPath = posterPath,
    backdropPath = backdropPath ?: "",
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    popularity = popularity,
    adult = adult,
    voteCount = voteCount
)

fun DBMovie.toMovieDTO() = MovieDTO(
    id = id,
    overview = overview,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    video = video,
    title = title,
    genreIds = listOf(genreIds),
    posterPath = posterPath,
    backdropPath = backdropPath,
    releaseDate = releaseDate,
    voteAverage = voteAverage,
    popularity = popularity,
    adult = adult,
    voteCount = voteCount
)