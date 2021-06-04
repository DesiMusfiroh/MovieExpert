package com.made.movieexpert.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.usecase.CatalogueUseCase

class FavoriteMovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<Movie>> = catalogueUseCase.getFavoriteMovies()
}
