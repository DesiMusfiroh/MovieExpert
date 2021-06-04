package com.made.movieexpert.ui.favorite.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.usecase.CatalogueUseCase

class FavoriteTvShowViewModel (private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun getFavoriteTvShows(): LiveData<List<TvShow>> = catalogueUseCase.getFavoriteTvShows()
}
