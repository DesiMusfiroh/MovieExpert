package com.made.movieexpert.ui.detail.tvshow

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.made.movieexpert.R
import com.made.movieexpert.data.model.Season
import com.made.movieexpert.databinding.ItemsSeasonBinding
import com.made.movieexpert.utils.Constants

class SeasonAdapter : RecyclerView.Adapter<SeasonAdapter.SeasonViewHolder>() {

    private val listSeasons = ArrayList<Season>()

    fun setSeasons(seasons: List<Season>?) {
        if (seasons == null) return
        this.listSeasons.clear()
        this.listSeasons.addAll(seasons)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeasonViewHolder {
        val itemsSeasonBinding = ItemsSeasonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeasonViewHolder(itemsSeasonBinding)
    }

    override fun onBindViewHolder(holder: SeasonViewHolder, position: Int) {
        val season = listSeasons[position]
        holder.bind(season)
    }

    override fun getItemCount(): Int = listSeasons.size

    inner class SeasonViewHolder(private val binding: ItemsSeasonBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(season: Season) {
            with(binding) {
                tvItemNumber.text = StringBuilder("Season ${season.number}")
                Glide.with(itemView.context)
                    .load(Constants.POSTER_URL + season.poster)
                    .apply(
                         RequestOptions.placeholderOf(R.drawable.ic_loading)
                              .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}