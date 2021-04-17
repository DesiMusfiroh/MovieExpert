package com.example.moviecatalogue.ui.home

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.moviecatalogue.R
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.databinding.ItemsCatalogueBinding
import com.example.moviecatalogue.ui.detail.DetailActivity

class CatalogueAdapter : RecyclerView.Adapter<CatalogueAdapter.CatalogueViewHolder>() {
    private var listCatalogues = ArrayList<CatalogueEntity>()

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
    }

    class CatalogueViewHolder(private val binding: ItemsCatalogueBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(catalogue: CatalogueEntity) {
            with(binding) {
                tvItemName.text = catalogue.name
                tvItemDesc.text = catalogue.desc
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.EXTRA_CONTENT, catalogue.id)
                    itemView.context.startActivity(intent)
                }
                Glide.with(itemView.context)
                    .load(catalogue.img_preview)
                    .apply(
                        RequestOptions.placeholderOf(R.drawable.ic_loading)
                        .error(R.drawable.ic_error))
                    .into(imgPoster)
            }
        }
    }
}