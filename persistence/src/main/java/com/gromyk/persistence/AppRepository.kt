package com.gromyk.persistence

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.gromyk.persistence.appdb.AppDB
import com.gromyk.persistence.model.genres.DBGenre
import com.gromyk.persistence.model.genres.GenreDAO
import com.gromyk.persistence.model.genres.movie.DBMovie
import com.gromyk.persistence.model.genres.movie.MoviesDAO


/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

class AppRepository(application: Application) {
    private var genreDAO: GenreDAO
    var genres: MutableLiveData<List<DBGenre>> = MutableLiveData()
        private set

    private var movieDAO: MoviesDAO
    var movies: MutableLiveData<List<DBMovie>> = MutableLiveData()
        private set


    init {
        val db = AppDB.getInstance(application)
        genreDAO = db?.genreDAO()!!
        genres.postValue(genreDAO.getGenres())
        movieDAO = db.movieDAO()
        movies.postValue(movieDAO.getMovies())
    }

    fun insertGenres(genres: List<DBGenre>) {
        genreDAO.insert(genres)
    }

    fun insertMovies(genres: List<DBMovie>) {
        movieDAO.insert(genres)
    }
}
