package com.gromyk.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle() = lifecycleRegistry
    private lateinit var tmdbViewModel: TmdbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
    }

    private fun initViewModel() {
        tmdbViewModel = ViewModelProviders.of(this).get(TmdbViewModel::class.java)

        tmdbViewModel.fetchMovies()

        tmdbViewModel.popularMoviesLiveData.observe(this@MainActivity, Observer { list ->
            val text = buildString {
                list.forEach { tmdbMovie ->
                    append("$tmdbMovie \n")
                }
            }
            textView.text = text
        })
    }
}
