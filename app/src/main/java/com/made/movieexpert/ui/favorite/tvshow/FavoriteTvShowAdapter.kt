package com.made.movieexpert.ui.favorite.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.R
import com.made.movieexpert.databinding.ItemsCatalogueFavoriteBinding
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.ui.detail.tvshow.DetailTvShowActivity
import com.made.movieexpert.utils.Constants

class FavoriteTvShowAdapter : RecyclerView.Adapter<FavoriteTvShowAdapter.TvShowViewHolder>() {
    private var listTvShows = ArrayList<TvShow>()

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

                itemView.setOnClickListener {
                    val intent =  Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}
