/**
 * Created by Yurii Gromyk
 * @date 3/3/19 3:54 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.gromyk.playground.R
import com.gromyk.playground.ui.MainActivity
import com.gromyk.playground.ui.base.BaseActivity


class SplashActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.splash_activity)
        Handler().postDelayed(
            { startMainActivity() },
            1500
        )
    }

    private fun startMainActivity() {
        val mainActivityIntent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        startActivity(mainActivityIntent)
    }

}