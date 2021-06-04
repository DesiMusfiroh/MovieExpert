package com.made.movieexpert.ui.favorite.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.capstone.movieexpert.core.domain.usecase.CatalogueUseCase

class FavoriteTvShowViewModel (private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val favoriteTvShows = catalogueUseCase.getFavoriteTvShows().asLiveData()
}
