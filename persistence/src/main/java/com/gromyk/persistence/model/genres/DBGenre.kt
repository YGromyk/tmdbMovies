package com.gromyk.persistence.model.genres

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

@Entity(tableName = DBGenre.TABLE_NAME, indices = [Index(value = [(DBGenre.COLUMN_GENRE_ID)], unique = true)])
data class DBGenre(
    @PrimaryKey @ColumnInfo(name = COLUMN_GENRE_ID) var id: Int,
    @ColumnInfo(name = "genreName") var name: String
) {
    companion object {
        const val COLUMN_GENRE_ID = "genreId"
        const val TABLE_NAME = "Genres"
    }
}