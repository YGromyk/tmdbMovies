/**
 * Created by Yurii Gromyk
 * @date 3/6/19 9:51 PM
 * @author gromyk
 *
 * Copyright (c) 2019.
 **/

package com.gromyk.playground.ui.genre

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.gromyk.playground.R
import com.gromyk.playground.api.dtos.genres.GenreDTO
import com.gromyk.playground.ui.base.recycler.BaseRecyclerAdapter
import com.gromyk.playground.utils.loadPhoto

class GenreAdapter(val listener: OnGenreSelected) : BaseRecyclerAdapter<GenreDTO>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false))
    }

    inner class ViewHolder(itemView: View) : BaseRecyclerAdapter.ViewHolder<GenreDTO>(itemView), View.OnClickListener {
        private val genreTextView: TextView by lazy { itemView.findViewById<TextView>(R.id.genreTextView) }
        private val genreImageView: ImageView by lazy { itemView.findViewById<ImageView>(R.id.genreImageView) }
        override fun bindView(item: GenreDTO) {
            genreImageView.loadPhoto("https://previews.123rf.com/images/darijashka/darijashka1610/darijashka161000003/66906323-vector-set-of-movie-genres-line-icons-made-in-circle-isolated-on-white-background-different-film-gen.jpg")
            genreTextView.text = item.name
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.clickOnGenre(items[adapterPosition])
        }
    }

    interface OnGenreSelected {
        fun clickOnGenre(item: GenreDTO)
    }
}