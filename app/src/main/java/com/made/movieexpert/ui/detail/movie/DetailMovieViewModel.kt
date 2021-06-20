package com.made.movieexpert.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.made.movieexpert.core.domain.model.Movie
import com.made.movieexpert.core.domain.usecase.CatalogueUseCase

class DetailMovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun setFavorite(movie: Movie, newStatus:Boolean) = catalogueUseCase.setMovieFavorite(movie, newStatus)
}
