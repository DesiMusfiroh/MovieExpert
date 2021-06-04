package com.made.movieexpert.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.usecase.CatalogueUseCase
import com.made.movieexpert.vo.Resource

class MovieViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun getMovies(): LiveData<Resource<List<Movie>>> = catalogueUseCase.getMovies(1)
}