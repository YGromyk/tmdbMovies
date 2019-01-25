package com.gromyk.persistence.model.genres.movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gromyk.persistence.model.genres.movie.DBMovie.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME, indices = [androidx.room.Index(value = [(DBMovie.COLUMN_ID)], unique = true)])
data class DBMovie(
    @PrimaryKey @ColumnInfo(name = "id")
    val id: Int = 0,
    @ColumnInfo(name = "overview")
    val overview: String = "",
    @ColumnInfo(name = "original_language")
    val originalLanguage: String = "",
    @ColumnInfo(name = "original_title")
    val originalTitle: String = "",
    @ColumnInfo(name = "video")
    val video: Boolean = false,
    @ColumnInfo(name = "title")
    val title: String = "",
    @ColumnInfo(name = "genre_ids")
    val genreIds: Int,
    @ColumnInfo(name = "poster_path")
    val posterPath: String = "",
    @ColumnInfo(name = "backdrop_path")
    val backdropPath: String = "",
    @ColumnInfo(name = "release_date")
    val releaseDate: String = "",
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double = 0.0,
    @ColumnInfo(name = "popularity")
    val popularity: Double = 0.0,
    @ColumnInfo(name = "adult")
    val adult: Boolean = false,
    @ColumnInfo(name = "vote_count")
    val voteCount: Int = 0
) {
    companion object {
        const val COLUMN_ID = "id"
        const val TABLE_NAME = "movies"
    }
}