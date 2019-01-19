package com.gromyk.persistence.appdb

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

/**
 * Created by Yuriy Gromyk on 1/20/19.
 */

object Migrations {
    val MIGRATION_ADD_MOVIES_TABLE = object : Migration(1, 2) {
        private val tableScript =
            "CREATE TABLE IF NOT EXISTS `movies` (" +
                    "`id` INTEGER NOT NULL, " +
                    "`overview` TEXT NOT NULL," +
                    " `original_language` TEXT NOT NULL, " +
                    "`original_title` TEXT NOT NULL, " +
                    "`video` INTEGER NOT NULL, " +
                    "`title` TEXT NOT NULL, " +
                    "`genre_ids` INTEGER NOT NULL, " +
                    "`poster_path` TEXT NOT NULL, " +
                    "`backdrop_path` TEXT NOT NULL, " +
                    "`release_date` TEXT NOT NULL, " +
                    "`vote_average` REAL NOT NULL, " +
                    "`popularity` REAL NOT NULL, " +
                    "`adult` INTEGER NOT NULL, " +
                    "`vote_count` INTEGER NOT NULL, " +
                    "PRIMARY KEY(`id`)" +
                    ")"
        private val indexation = "CREATE UNIQUE INDEX `index_movies_id` ON `movies` (`id`)"

        override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL(tableScript)
            database.execSQL(indexation)
        }
    }
}