package com.made.movieexpert.ui.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.vo.Resource

@Suppress("DEPRECATION")
class MovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getMovies(): LiveData<Resource<PagedList<MovieEntity>>> = catalogueRepository.getMovies(1)
}