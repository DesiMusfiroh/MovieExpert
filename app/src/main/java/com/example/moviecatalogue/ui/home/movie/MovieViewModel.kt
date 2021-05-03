package com.example.moviecatalogue.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.source.CatalogueRepository

class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovies(): LiveData<List<Movie>> = catalogueRepository.getMovies(1)
}