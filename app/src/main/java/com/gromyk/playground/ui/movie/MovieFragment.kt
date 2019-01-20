package com.gromyk.playground.ui.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.gromyk.playground.R
import kotlinx.android.synthetic.main.fragment_movie.*
import timber.log.Timber


class MovieFragment : Fragment() {
    private lateinit var viewModel: MovieViewModel
    private var movieId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getExtras()
        initViewModel()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MovieViewModel::class.java)
    }

    private fun initView() {
        nameTextView.text = movieId?.toString()
        Timber.d("movie id -> $movieId")
    }

    private fun getExtras() {
        movieId = arguments?.getInt("movieId")
    }
}