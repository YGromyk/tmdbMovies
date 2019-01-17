package com.gromyk.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.lang.RuntimeException

class MainActivity : AppCompatActivity(), LifecycleOwner {
    private val lifecycleRegistry by lazy { LifecycleRegistry(this) }
    override fun getLifecycle() = lifecycleRegistry
    private lateinit var tmdbViewModel: TmdbViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()
        GlobalScope.launch {
            delay(5000)
        }
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
