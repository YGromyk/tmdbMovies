package com.gromyk.playground.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gromyk.playground.R
import com.gromyk.playground.api.dtos.movies.MovieDTO
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * Created by Yuriy Gromyk on 1/18/19.
 */

class MoviesFragment : Fragment(), MovieAdapter.OnMovieSelected {
    lateinit var viewModel: TmdbViewModel
    private lateinit var adapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        contentView.layoutManager = LinearLayoutManager(context).apply {
            orientation = RecyclerView.VERTICAL
        }
        contentView.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )
        swipeRefreshLayout.setOnRefreshListener { viewModel.fetchMovies() }
        viewModel.fetchMovies()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(TmdbViewModel::class.java)
        viewModel.apply {
            popularMoviesLiveData.observe(this@MoviesFragment, Observer {
                onMoviesLoaded(it)
            })
        }
    }

    private fun onMoviesLoaded(movies: List<MovieDTO>) {
        swipeRefreshLayout.isRefreshing = false
        adapter = MovieAdapter(movies, this)
        contentView.adapter = adapter
    }

    override fun clickOnMovie(movie: MovieDTO) {

    }

    companion object {
        @Suppress("unused")
        fun newInstance() = MoviesFragment()
    }
}