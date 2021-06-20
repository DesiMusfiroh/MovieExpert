package com.made.movieexpert.ui.home.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.movieexpert.core.domain.usecase.CatalogueUseCase

class MovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val movies = catalogueUseCase.getMovies(1).asLiveData()
}