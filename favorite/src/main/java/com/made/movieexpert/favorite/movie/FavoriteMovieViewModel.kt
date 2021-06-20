package com.made.movieexpert.favorite.movie

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.made.movieexpert.core.domain.usecase.CatalogueUseCase

class FavoriteMovieViewModel(catalogueUseCase: CatalogueUseCase) : ViewModel() {
    val favoriteMovies = catalogueUseCase.getFavoriteMovies().asLiveData()
}
