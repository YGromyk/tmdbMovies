package com.gromyk.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gromyk.persistence.model.genres.DBGenre
import com.gromyk.persistence.model.genres.GenreDAO

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */
@Database(
    entities = [DBGenre::class],
    version = 1
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
                        .build()
                }
            }
            return INSTANCE
        }
    }

    abstract fun genreDAO(): GenreDAO
}