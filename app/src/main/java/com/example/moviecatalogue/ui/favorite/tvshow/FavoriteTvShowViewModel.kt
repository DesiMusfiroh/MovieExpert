package com.example.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel (private val catalogueRepository: CatalogueRepository) : ViewModel() {
    @Suppress("DEPRECATION")
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        return catalogueRepository.getFavoriteTvShows()
    }
}
