package com.gromyk.persistence

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.MutableLiveData
import com.gromyk.persistence.model.genres.DBGenre
import com.gromyk.persistence.model.genres.GenreDAO


/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

class AppRepository(application: Application) {
    private var genreDAO: GenreDAO
    var genres: MutableLiveData<List<DBGenre>> = MutableLiveData()
        private set

    init {
        val db = AppDB.getInstance(application)
        genreDAO = db?.genreDAO()!!
        genres.postValue(genreDAO.getGenres())
    }

    fun insert(genres: List<DBGenre>) {
        InsertAsyncTask(genreDAO).execute(genres)
    }

    private class InsertAsyncTask internal constructor(private val mAsyncTaskDao: GenreDAO) :
        AsyncTask<List<DBGenre>, Void, Void>() {

        override fun doInBackground(vararg params: List<DBGenre>): Void? {
            mAsyncTaskDao.insert(params[0])
            return null
        }
    }
}
