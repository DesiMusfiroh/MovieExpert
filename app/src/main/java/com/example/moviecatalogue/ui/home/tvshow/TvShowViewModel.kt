package com.example.moviecatalogue.ui.home.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy

class TvShowViewModel : ViewModel() {
    fun getTvShow(): List<CatalogueEntity> = DataDummy.generateDummyTvShows()
}