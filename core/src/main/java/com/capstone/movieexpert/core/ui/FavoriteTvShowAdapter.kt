package com.capstone.movieexpert.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.capstone.movieexpert.core.R
import com.capstone.movieexpert.core.databinding.ItemsCatalogueFavoriteBinding
import com.capstone.movieexpert.core.domain.model.TvShow
import com.capstone.movieexpert.core.utils.Constants

class FavoriteTvShowAdapter : RecyclerView.Adapter<FavoriteTvShowAdapter.TvShowViewHolder>() {
    private var listTvShows = ArrayList<TvShow>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: TvShow)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setTvShows(tvShows: List<TvShow>?) {
        if (tvShows == null) return
        this.listTvShows.clear()
        this.listTvShows.addAll(tvShows)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsCatalogueFavoriteBinding = ItemsCatalogueFavoriteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsCatalogueFavoriteBinding)
    }

    override fun getItemCount(): Int = listTvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTvShows[holder.adapterPosition])
        }
    }

    class TvShowViewHolder(private val binding: ItemsCatalogueFavoriteBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                tvItemName.text = tvShow.name
                tvItemDesc.text = tvShow.desc
                tvItemRating.text = tvShow.rating.toString()
                tvItemPopularity.text = StringBuilder("Popularity : ${tvShow.popularity}")
                Glide.with(itemView.context)
                        .load(Constants.POSTER_URL + tvShow.poster)
                        .apply(
                                RequestOptions.placeholderOf(R.drawable.ic_loading)
                                        .error(R.drawable.ic_error))
                        .into(imgPoster)
            }
        }
    }
}
