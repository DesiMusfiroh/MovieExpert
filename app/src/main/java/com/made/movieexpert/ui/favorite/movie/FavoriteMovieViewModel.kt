package com.made.movieexpert.ui.favorite.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.MovieEntity

@Suppress("DEPRECATION")
class FavoriteMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> = catalogueRepository.getFavoriteMovies()
}
