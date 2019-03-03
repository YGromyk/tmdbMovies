package com.gromyk.persistence.model.genres

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import com.gromyk.persistence.model.genres.DBGenre.Companion.COLUMN_GENRE_ID
import com.gromyk.persistence.model.genres.DBGenre.Companion.TABLE_NAME

/**
 * Created by Yurii Gromyk on 1/19/19.
 */
@Dao
abstract class GenreDAO {
    @Insert(onConflict = REPLACE)
    abstract fun insert(obj: List<DBGenre>)

    @Query("select * from $TABLE_NAME where $COLUMN_GENRE_ID = :id")
    abstract fun getGenre(id: Int): DBGenre

    @Query("select * from $TABLE_NAME")
    abstract fun getGenres(): List<DBGenre>

    @Query("delete from $TABLE_NAME")
    abstract fun clear()
}