package com.made.movieexpert.ui.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.movieexpert.domain.usecase.CatalogueUseCase

class FavoriteMovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val favoriteMovies = catalogueUseCase.getFavoriteMovies().asLiveData()
}
