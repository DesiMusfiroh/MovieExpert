package com.made.movieexpert.ui.home.tvshow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.movieexpert.core.domain.usecase.CatalogueUseCase

class TvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val tvShows = catalogueUseCase.getTvShows(1).asLiveData()
}