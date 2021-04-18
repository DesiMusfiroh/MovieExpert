package com.example.moviecatalogue.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.databinding.ItemsCatalogueBinding

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.CatalogueViewHolder>() {
    private var listCatalogues = ArrayList<CatalogueEntity>()
    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onItemClicked(data: CatalogueEntity)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    fun setCatalogues(catalogues: List<CatalogueEntity>?) {
        if (catalogues == null) return
        this.listCatalogues.clear()
        this.listCatalogues.addAll(catalogues)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatalogueViewHolder {
        val itemsCatalogueBinding = ItemsCatalogueBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CatalogueViewHolder(itemsCatalogueBinding)
    }

    override fun getItemCount(): Int = listCatalogues.size

    override fun onBindViewHolder(holder: CatalogueViewHolder, position: Int) {
        val catalogue = listCatalogues[position]
        holder.bind(catalogue)
        holder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listCatalogues[holder.adapterPosition])
        }
    }

    class CatalogueViewHolder(private val binding: ItemsCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(catalogue: CatalogueEntity) {
            with(binding) {
                tvItemName.text = catalogue.name
                tvItemDate.text = catalogue.date
                Glide.with(itemView.context)
                    .load(catalogue.poster)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}