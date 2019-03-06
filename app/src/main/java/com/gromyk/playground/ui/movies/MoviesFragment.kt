package com.gromyk.playground.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.gromyk.playground.R
import com.gromyk.playground.api.dtos.genres.GenreDTO
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.ui.base.BaseFragment
import com.gromyk.playground.ui.base.recycler.CenterLayoutManager
import com.gromyk.playground.ui.genre.GenreAdapter
import com.gromyk.playground.utils.networkstate.NetworkState
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.progress_bar_layout.*
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * Created by Yurii Gromyk on 1/18/19.
 */

class MoviesFragment : BaseFragment(),
    MovieAdapter.OnMovieSelected,
    GenreAdapter.OnGenreSelected {
    override val viewModel by viewModel<TmdbViewModel>()
    private lateinit var moviesAdapter: MovieAdapter
    private lateinit var genresAdapter: GenreAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_movies, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    private fun initView() {
        contentViewMovies.layoutManager = CenterLayoutManager(
            context = activity!!,
            orientation = RecyclerView.HORIZONTAL,
            reverseLayout = false
        )
        moviesSwipeRefreshLayout.setOnRefreshListener { viewModel.fetchMovies() }
        moviesAdapter = MovieAdapter(this)
        contentViewMovies.adapter = moviesAdapter

        contentViewGenres.layoutManager = CenterLayoutManager(
            context = activity!!,
            orientation = RecyclerView.HORIZONTAL,
            reverseLayout = false
        )
        genresSwipeRefreshLayout.setOnRefreshListener { viewModel.fetchGenres() }
        genresAdapter = GenreAdapter(this)
        contentViewGenres.adapter = genresAdapter

        viewModel.fetchData()
    }

    private fun initViewModel() {
        viewModel.apply {
            popularMoviesLiveData.observe(this@MoviesFragment, Observer {
                onMoviesLoaded(it)
            })
            genresLiveData.observe(this@MoviesFragment, Observer { onGenresLoaded(it) })
            networkState.observe(this@MoviesFragment, Observer { onNetworkStateChanged(it!!) })
        }
    }

    private fun onMoviesLoaded(movies: List<MovieDTO>) {
        moviesSwipeRefreshLayout.isRefreshing = false
        moviesAdapter.replaceItems(movies)
    }

    private fun onGenresLoaded(items: List<GenreDTO>) {
        genresSwipeRefreshLayout.isRefreshing = false
        genresAdapter.replaceItems(items)
    }

    override fun onNetworkStateChanged(networkState: NetworkState) {
        when (networkState.status) {
            NetworkState.LOADING -> {
                progressBar?.visibility = View.VISIBLE
                contentViewMovies.visibility = View.GONE
            }
            NetworkState.FAILED -> {
            }
            NetworkState.SUCCESS -> {
                progressBar?.visibility = View.GONE
                contentViewMovies.visibility = View.VISIBLE
            }
        }
    }

    override fun clickOnMovie(movie: MovieDTO) {
        view?.findNavController()?.apply {
            val bundle = bundleOf("movieId" to movie.id)
            navigate(R.id.action_moviesFragment_to_movieFragment, bundle)
        }
    }

    override fun clickOnGenre(item: GenreDTO) {

    }

    companion object {
        @Suppress("unused")
        fun newInstance() = MoviesFragment()
    }
}