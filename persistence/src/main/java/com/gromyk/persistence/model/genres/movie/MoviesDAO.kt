package com.gromyk.persistence.model.genres.movie

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gromyk.persistence.model.genres.movie.DBMovie.Companion.COLUMN_ID
import com.gromyk.persistence.model.genres.movie.DBMovie.Companion.TABLE_NAME

/**
 * Created by Yuriy Gromyk on 1/20/19.
 */

@Dao
abstract class MoviesDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(obj: List<DBMovie>)

    @Query("select * from $TABLE_NAME where $COLUMN_ID = :id")
    abstract fun getMovie(id: Int): DBMovie

    @Query("select * from $TABLE_NAME")
    abstract fun getMovies(): List<DBMovie>

    @Query("delete from $TABLE_NAME")
    abstract fun clear()
}