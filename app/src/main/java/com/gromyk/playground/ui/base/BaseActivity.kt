/**
 * Created by Yurii Gromyk
 * @date 3/2/19 5:37 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui.base

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity(), ToolbarInteractor {
    override fun setHomeIcon(iconId: Int) {
        supportActionBar?.setIcon(iconId)
    }

    override fun setToolbarTitle(title: String?) {
        supportActionBar?.title = title
    }
}