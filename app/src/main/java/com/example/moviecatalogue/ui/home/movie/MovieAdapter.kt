package com.example.moviecatalogue.ui.home.movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.databinding.ItemsCatalogueBinding
import com.example.moviecatalogue.utils.Constants.POSTER_URL

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<MovieEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: MovieEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<MovieEntity>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsCatalogueBinding = ItemsCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsCatalogueBinding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            @Suppress("DEPRECATION")
            onItemClickCallback.onItemClicked(listMovies[holder.adapterPosition])
        }
    }

    class MovieViewHolder(private val binding: ItemsCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemName.text = movie.name
                tvItemDate.text = movie.date
                tvItemRating.text = movie.rating.toString()
                Glide.with(itemView.context)
                    .load(POSTER_URL + movie.poster)
                    .apply(
                         RequestOptions.placeholderOf(R.drawable.ic_loading)
                              .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}