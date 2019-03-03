/**
 * Created by Yurii Gromyk
 * @date 3/2/19 6:48 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.utils.resources

import android.content.Context
import android.content.res.Resources
import android.graphics.drawable.Drawable
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

@Suppress("unused")
class ResourceProviderImpl : ResourceProvider {
    lateinit var context: Context

    override fun getString(@StringRes id: Int): String? = context.getString(id)
    override fun getColor(@ColorRes id: Int): Int? = ContextCompat.getColor(context, id)
    override fun getDrawable(id: Int): Drawable? = ContextCompat.getDrawable(context, id)
    override fun getTheme(): Resources.Theme = context.theme
}