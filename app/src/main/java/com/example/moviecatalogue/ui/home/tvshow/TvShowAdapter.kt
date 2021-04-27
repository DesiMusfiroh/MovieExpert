package com.example.moviecatalogue.ui.home.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.databinding.ItemsCatalogueBinding
import com.example.moviecatalogue.utils.Constants.POSTER_URL

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {
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
        val itemsCatalogueBinding = ItemsCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsCatalogueBinding)
    }

    override fun getItemCount(): Int = listTvShows.size

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow = listTvShows[position]
        holder.bind(tvShow)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listTvShows[holder.adapterPosition])
        }
    }

    class TvShowViewHolder(private val binding: ItemsCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShow) {
            with(binding) {
                tvItemName.text = tvShow.name
                tvItemDate.text = tvShow.date
                tvItemRating.text = tvShow.rating.toString()
                Glide.with(itemView.context)
                    .load(POSTER_URL + tvShow.poster)
                    .apply(
                         RequestOptions.placeholderOf(R.drawable.ic_loading)
                         .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}