package com.gromyk.playground.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.gromyk.playground.R
import com.gromyk.playground.api.BaseUrl
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.ui.base.BaseFragment
import com.gromyk.playground.utils.loadPhoto
import com.gromyk.playground.utils.networkstate.NetworkState
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.progress_bar_layout.*


class MovieFragment : BaseFragment() {
    private lateinit var viewModel: MovieViewModel
    private var movieId: Int? = null
    override val progressView: ProgressBar? by lazy {
        progressBar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
        viewModel.movieData.observe(this@MovieFragment, Observer { initViewWith(movie = it!!) })
    }

    private fun initView() {
        nameTextView.text = movieId?.toString()
        viewModel.fetchMovieBy(movieId!!)
    }

    private fun initViewWith(movie: MovieDTO) {
        movie.apply {
            nameTextView.text = title
            releaseDateTextView.text = releaseDate
            descriptionTextView.text = overview
            rateTextView.text = voteAverage.toString()
            movieImageView.loadPhoto(BaseUrl.BASE_IMAGE_URL + backdropPath)
        }
    }

    private fun getExtras() {
        movieId = arguments?.getInt("movieId")
    }

    override fun onNetworkStateChanged(networkState: NetworkState) {
        when (networkState.status) {
            NetworkState.LOADING -> {
                progressView?.visibility = View.VISIBLE
                contentView.visibility = View.GONE
            }
            NetworkState.FAILED -> {

            }
            NetworkState.SUCCESS -> {
                progressView?.visibility = View.GONE
                contentView.visibility = View.VISIBLE
            }
        }
    }
}