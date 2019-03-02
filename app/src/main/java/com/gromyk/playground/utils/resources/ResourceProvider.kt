/**
 * Created by Yurii Gromyk
 * @date 3/2/19 6:48 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.utils.resources

import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface ResourceProvider {
    fun getString(@StringRes id: Int): String?
    fun getColor(@ColorRes id: Int): Int?
    fun getDrawable(@DrawableRes id: Int): Drawable?
    fun getTheme(): Resources.Theme
}