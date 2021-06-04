package com.made.movieexpert.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.movieexpert.core.domain.usecase.CatalogueUseCase

class FavoriteTvShowViewModel (private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val favoriteTvShows = catalogueUseCase.getFavoriteTvShows().asLiveData()
}
