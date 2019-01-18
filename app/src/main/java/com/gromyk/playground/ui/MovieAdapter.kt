package com.gromyk.playground.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gromyk.playground.R
import com.gromyk.playground.api.dtos.TmdbMovie

/**
 * Created by Yuriy Gromyk on 1/18/19.
 */

class MovieAdapter(private val items: List<TmdbMovie>, var listener: OnMovieSelected) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindView(items[position])
    }

    inner class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {
        private val nameTextView: TextView by lazy { view.findViewById<TextView>(R.id.nameTextView) }
        private val descriptionTextView: TextView by lazy { view.findViewById<TextView>(R.id.descriptionTextView) }
        private val rateTextView: TextView by lazy { view.findViewById<TextView>(R.id.rateTextView) }
        fun bindView(item: TmdbMovie) {
            itemView.setOnClickListener { listener.clickOnMovie(item) }
            nameTextView.text = item.title
            descriptionTextView.text = item.overview
            rateTextView.text = item.vote_average.toString()
        }
    }

    interface OnMovieSelected {
        fun clickOnMovie(movie: TmdbMovie)
    }
}