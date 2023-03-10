package com.example.fitopenpay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.MovieItem
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieBinding

class MoviesAdapter(private val movies: List<MovieItem>) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size

    class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemMovieBinding.bind(itemView)

        fun bind(item: MovieItem) {
            binding.apply {
                movieTitle.text = item.name ?: item.title
                Glide.with(itemView.context)
                    .load("$BASE_IMAGE_URL${item.image}")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .centerCrop()
                    .into(movieImage)
                movieScore.text = item.score.toString()
                movieYear.text = item.year
            }
        }
    }
    companion object {
        const val BASE_IMAGE_URL = "https://image.tmdb.org/t/p/w500"
    }
}


