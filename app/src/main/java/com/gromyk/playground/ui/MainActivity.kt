package com.gromyk.playground.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.gromyk.playground.R

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
    }

    private fun replaceFragment() {
        Navigation.findNavController(this, R.id.nav_host).navigate(R.id.moviesFragment)
    }
}
