/**
 * Created by Yurii Gromyk
 * @date 3/3/19 1:44 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui.base

import androidx.annotation.DrawableRes

interface ToolbarInteractor {
    fun setToolbarTitle(title: String?)
    fun setHomeIcon(@DrawableRes iconId: Int)
}