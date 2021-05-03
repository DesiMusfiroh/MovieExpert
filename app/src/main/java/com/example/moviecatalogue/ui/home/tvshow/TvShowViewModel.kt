package com.example.moviecatalogue.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.CatalogueRepository

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    fun getTvShows(): LiveData<List<TvShow>> = catalogueRepository.getTvShows(1)
}