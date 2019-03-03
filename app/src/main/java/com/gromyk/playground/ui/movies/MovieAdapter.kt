package com.gromyk.playground.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gromyk.playground.R
import com.gromyk.playground.api.BaseUrl
import com.gromyk.playground.api.dtos.movies.MovieDTO
import com.gromyk.playground.utils.loadPhoto

/**
 * Created by Yurii Gromyk on 1/18/19.
 */

class MovieAdapter(private val items: List<MovieDTO>, var listener: OnMovieSelected) :
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
        private val movieImageView: ImageView by lazy { view.findViewById<ImageView>(R.id.movieImageView) }
        private val releaseDateTextView: TextView by lazy { view.findViewById<TextView>(R.id.releaseDateTextView) }
        private val genresTextView: TextView by lazy { view.findViewById<TextView>(R.id.genresTextView) }

        fun bindView(item: MovieDTO) {
            itemView.setOnClickListener { listener.clickOnMovie(item) }
            nameTextView.text = item.title
            descriptionTextView.text = item.overview
            releaseDateTextView.text = item.releaseDate
            movieImageView.loadPhoto(BaseUrl.BASE_IMAGE_URL + item.backdropPath)
            val genres = buildString {
                item.genresNames.forEach {
                    append(it)
                    append(if (it === item.genresNames.last()) "." else ", ")
                }
            }
            genresTextView.text = genres
        }
    }

    interface OnMovieSelected {
        fun clickOnMovie(movie: MovieDTO)
    }
}