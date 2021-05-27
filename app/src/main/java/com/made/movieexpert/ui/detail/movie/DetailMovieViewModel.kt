package com.made.movieexpert.ui.detail.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.vo.Resource

class DetailMovieViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val movieId = MutableLiveData<Int>()

    fun setSelectedMovie(movieId: Int) {
        this.movieId.value = movieId
    }

    var getMovie: LiveData<Resource<MovieEntity>> = Transformations.switchMap(movieId) { mMovieId ->
        catalogueRepository.getMovie(mMovieId)
    }

    fun setFavorite() {
        val movieResource = getMovie.value
        val movieEntity = movieResource?.data
        val newState = !movieEntity?.favorited!!
        catalogueRepository.setMovieFavorite(movieEntity, newState)
    }
}