package com.example.moviecatalogue.ui.detail.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy
import kotlin.properties.Delegates

class DetailMovieViewModel : ViewModel() {
    private var movieId: Int = 0

    private fun getListMovie(): ArrayList<CatalogueEntity> = DataDummy.generateDummyMovies() as ArrayList<CatalogueEntity>

    fun setSelectedMovie(movieId: Int) {
        this.movieId = movieId
    }

    fun getMovie(): CatalogueEntity {
        lateinit var result: CatalogueEntity
        val listMovie = getListMovie()
        for (movieEntity in listMovie) {
            if (movieEntity.id == movieId) result = movieEntity
        }
        return result
    }
}