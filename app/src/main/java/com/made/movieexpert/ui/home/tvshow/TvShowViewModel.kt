package com.made.movieexpert.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.usecase.CatalogueUseCase
import com.made.movieexpert.vo.Resource

class TvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    fun getTvShows(): LiveData<Resource<List<TvShow>>> = catalogueUseCase.getTvShows(1)
}