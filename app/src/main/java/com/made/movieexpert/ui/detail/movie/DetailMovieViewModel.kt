package com.made.movieexpert.ui.detail.movie

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.capstone.movieexpert.core.domain.model.Movie
import com.capstone.movieexpert.core.domain.usecase.CatalogueUseCase

class DetailMovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    private val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    fun setFavorite(movie: Movie, newStatus:Boolean) = catalogueUseCase.setMovieFavorite(movie, newStatus)
}
