package com.made.movieexpert.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import com.made.movieexpert.core.domain.model.TvShow
import com.made.movieexpert.core.domain.usecase.CatalogueUseCase

class DetailTvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun setFavorite(tvShow: TvShow, newStatus:Boolean) = catalogueUseCase.setTvShowFavorite(tvShow, newStatus)
}