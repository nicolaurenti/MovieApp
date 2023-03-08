package com.example.fitopenpay.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.domain.entity.MovieItem
import com.example.movieapp.R
import com.example.movieapp.databinding.ItemMovieBinding

class MoviesAdapter : ListAdapter<MovieItem, MoviesAdapter.MoviesViewHolder>(DiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        MoviesViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false))

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        holder.bind(getItem(position))
    }


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

        class DiffCallback : DiffUtil.ItemCallback<MovieItem>() {
            override fun areItemsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: MovieItem, newItem: MovieItem): Boolean = oldItem == newItem

        }
    }
}


