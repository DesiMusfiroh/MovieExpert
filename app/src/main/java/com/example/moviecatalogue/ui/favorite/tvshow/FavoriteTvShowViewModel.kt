package com.example.moviecatalogue.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

class FavoriteTvShowViewModel (private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> {
        return catalogueRepository.getFavoriteTvShows()
    }
}
