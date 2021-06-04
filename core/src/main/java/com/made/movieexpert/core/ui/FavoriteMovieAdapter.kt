package com.made.movieexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.core.R
import com.made.movieexpert.core.databinding.ItemsCatalogueFavoriteBinding
import com.made.movieexpert.core.domain.model.Movie
import com.made.movieexpert.core.utils.Constants

@Suppress("DEPRECATION")
class FavoriteMovieAdapter : RecyclerView.Adapter<FavoriteMovieAdapter.MovieViewHolder>() {
    private var listMovies = ArrayList<Movie>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: Movie)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setMovies(movies: List<Movie>?) {
        if (movies == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movies)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsCatalogueFavoriteBinding = ItemsCatalogueFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsCatalogueFavoriteBinding)
    }

    override fun getItemCount(): Int = listMovies.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(movie)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listMovies[holder.adapterPosition])
        }
    }

    class MovieViewHolder(private val binding: ItemsCatalogueFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            with(binding) {
                tvItemName.text = movie.name
                tvItemRating.text = movie.rating.toString()
                tvItemDesc.text = movie.desc
                tvItemPopularity.text = StringBuilder("Popularity : ${movie.popularity}")
                Glide.with(itemView.context)
                        .load(Constants.POSTER_URL + movie.poster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}
