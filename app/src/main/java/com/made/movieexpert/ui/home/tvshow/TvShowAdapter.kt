package com.made.movieexpert.ui.home.tvshow

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.R
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.databinding.ItemsCatalogueBinding
import com.made.movieexpert.ui.detail.tvshow.DetailTvShowActivity
import com.made.movieexpert.utils.Constants.POSTER_URL

class TvShowAdapter  : PagedListAdapter<TvShowEntity, TvShowAdapter.TvShowViewHolder>(DIFF_CALLBACK_TV_SHOW) {

    companion object {
        private val DIFF_CALLBACK_TV_SHOW = object : DiffUtil.ItemCallback<TvShowEntity>() {
            override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem.id == newItem.id
            }
            override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvShowViewHolder {
        val itemsCatalogueBinding = ItemsCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(itemsCatalogueBinding)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) {
        val tvShow: TvShowEntity? = getItem(position)
        if (tvShow != null) {
            holder.bind(tvShow)
        }
    }

    class TvShowViewHolder(private val binding: ItemsCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tvShow: TvShowEntity) {
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

                itemView.setOnClickListener {
                    val intent =  Intent(itemView.context, DetailTvShowActivity::class.java)
                    intent.putExtra(DetailTvShowActivity.EXTRA_TV_SHOW, tvShow.id)
                    itemView.context.startActivity(intent)
                }
            }
        }
    }
}