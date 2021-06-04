package com.made.movieexpert.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.usecase.CatalogueUseCase

class DetailMovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setFavorite(movie: Movie, newStatus:Boolean) = catalogueUseCase.setMovieFavorite(movie, newStatus)
}
