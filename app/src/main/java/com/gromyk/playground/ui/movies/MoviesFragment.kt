package com.gromyk.playground.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gromyk.playground.R
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.ui.MovieAdapter
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
            isDataLoading.observe(this@MoviesFragment, Observer { onDataLoaded(it!!) })
        }
    }

    private fun onMoviesLoaded(movies: List<MovieDTO>) {
        swipeRefreshLayout.isRefreshing = false
        adapter = MovieAdapter(movies, this)
        contentView.adapter = adapter
    }

    private fun onDataLoaded(isDataLoading: Boolean) {
        if (isDataLoading) {
            progressBar.visibility = View.VISIBLE
            contentView.visibility = View.GONE
        } else {
            progressBar.visibility = View.GONE
            contentView.visibility = View.VISIBLE
        }
    }

    override fun clickOnMovie(movie: MovieDTO) {
        view?.findNavController()?.apply {
            val bundle = bundleOf("movieId" to movie.id)
            navigate(R.id.action_moviesFragment_to_movieFragment, bundle)
        }
    }

    companion object {
        @Suppress("unused")
        fun newInstance() = MoviesFragment()
    }
}