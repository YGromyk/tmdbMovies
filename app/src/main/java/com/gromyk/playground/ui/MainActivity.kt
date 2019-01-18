package com.gromyk.playground.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gromyk.playground.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }

    private fun replaceFragment() {
        supportFragmentManager.beginTransaction()
                .replace(R.id.container, MoviesFragment.newInstance())
                .commit()
    }
}
