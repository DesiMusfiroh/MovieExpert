package com.made.movieexpert.ui.favorite.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.R
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.databinding.ItemsCatalogueFavoriteBinding
import com.made.movieexpert.ui.detail.movie.DetailMovieActivity
import com.made.movieexpert.utils.Constants

class FavoriteMovieAdapter : PagedListAdapter<MovieEntity, FavoriteMovieAdapter.MovieViewHolder>(DIFF_CALLBACK_MOVIE) {
    companion object {
        private val DIFF_CALLBACK_MOVIE = object : DiffUtil.ItemCallback<MovieEntity>() {
            override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val itemsCatalogueFavoriteBinding = ItemsCatalogueFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(itemsCatalogueFavoriteBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie: MovieEntity? = getItem(position)
        if (movie != null) {
            holder.bind(movie)
        }
    }

    class MovieViewHolder(private val binding: ItemsCatalogueFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieEntity) {
            with(binding) {
                tvItemName.text = movie.name
                tvItemDesc.text = movie.desc
                tvItemRating.text = movie.rating.toString()
                tvItemPopularity.text = StringBuilder("Popularity : ${movie.popularity}")
                Glide.with(itemView.context)
                        .load(Constants.POSTER_URL + movie.poster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgPoster)

                itemView.setOnClickListener {
                    val intent =  Intent(itemView.context, DetailMovieActivity::class.java)
                    intent.putExtra(DetailMovieActivity.EXTRA_MOVIE, movie.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}