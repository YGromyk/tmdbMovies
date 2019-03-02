/**
 * Created by Yurii Gromyk
 * @date 3/2/19 5:37 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui

import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.gromyk.playground.R
import com.gromyk.playground.ui.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.default_toolbar.*


class MainActivity : BaseActivity() {
    private lateinit var viewModel: MainViewModel

    private lateinit var navHostFragment: NavHostFragment
    private var clicksOnHome = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment()
        initToolbar()
        initViewModel()
    }

    private fun replaceFragment() {
        navHostFragment = nav_host as NavHostFragment
        Navigation.findNavController(this, R.id.nav_host).apply {
            if (currentDestination?.id == null) {
                navigate(R.id.moviesFragment)
                NavigationUI.setupActionBarWithNavController(this@MainActivity, this)
            }
        }
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back_black_24dp)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onBackPressed() {
        val currentFragment = navHostFragment.childFragmentManager.fragments[0]
        val controller = Navigation.findNavController(this, R.id.nav_host)
        if (currentFragment is OnBackPressedListener) {
            (currentFragment as OnBackPressedListener).onBackPressed()
            controller.popBackStack()
        } else if (!controller.popBackStack())
            onExitFromApplication()
    }

    private fun onExitFromApplication() {
        clicksOnHome++
        if (clicksOnHome == 2)
            finish()
        else
            Toast
                .makeText(this, "Click one more time to close app", Toast.LENGTH_SHORT)
                .show()
        Handler().postDelayed(
            { clicksOnHome = 0 },
            2000
        )
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
}
