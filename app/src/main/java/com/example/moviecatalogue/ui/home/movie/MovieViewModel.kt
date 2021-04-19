package com.example.moviecatalogue.ui.home.movie

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy

class MovieViewModel : ViewModel() {
    fun getMovies(): List<CatalogueEntity> = DataDummy.generateDummyMovies()
}