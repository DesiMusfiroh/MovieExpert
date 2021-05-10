package com.example.moviecatalogue.ui.favorite.movie

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity

class FavoriteMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteMovies(): LiveData<List<MovieEntity>> {
        return catalogueRepository.getFavoriteMovies()
    }
}
