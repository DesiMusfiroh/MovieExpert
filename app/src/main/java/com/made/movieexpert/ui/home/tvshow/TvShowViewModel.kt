package com.made.movieexpert.ui.home.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.vo.Resource

class TvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    @Suppress("DEPRECATION")
    fun getTvShows(): LiveData<Resource<PagedList<TvShowEntity>>> = catalogueRepository.getTvShows(1)
}