package com.gromyk.persistence.appdb

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gromyk.persistence.model.genres.DBGenre
import com.gromyk.persistence.model.genres.GenreDAO
import com.gromyk.persistence.model.genres.movie.DBMovie
import com.gromyk.persistence.model.genres.movie.MoviesDAO

/**
 * Created by Yurii Gromyk on 1/19/19.
 */
@Database(
    entities = [DBGenre::class, DBMovie::class],
    version = 2
)
abstract class AppDB : RoomDatabase() {
    companion object {
        private var INSTANCE: AppDB? = null

        fun getInstance(context: Context): AppDB? {
            if (INSTANCE == null) {
                synchronized(AppDB::class) {
                    INSTANCE = Room
                        .databaseBuilder(
                            context.applicationContext,
                            AppDB::class.java,
                            "movies.db"
                        )
                        .addMigrations(Migrations.MIGRATION_ADD_MOVIES_TABLE)
                        .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun genreDAO(): GenreDAO

    abstract fun movieDAO(): MoviesDAO
}