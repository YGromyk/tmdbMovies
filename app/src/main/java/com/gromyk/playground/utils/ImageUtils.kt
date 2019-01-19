package com.gromyk.playground.utils

import android.widget.ImageView
import com.gromyk.playground.R
import com.squareup.picasso.Picasso

/**
 * Created by Yuriy Gromyk on 1/19/19.
 */

fun ImageView.loadPhoto(url: String) {
    Picasso.get()
            .load(url)
            .placeholder(R.drawable.film_poster_placeholder)
            .into(this)
}