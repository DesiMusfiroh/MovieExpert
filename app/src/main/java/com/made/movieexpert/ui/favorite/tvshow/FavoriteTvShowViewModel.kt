package com.made.movieexpert.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel (private val catalogueRepository: CatalogueRepository) : ViewModel() {
    @Suppress("DEPRECATION")
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> = catalogueRepository.getFavoriteTvShows()
}
