package com.made.movieexpert.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.made.movieexpert.data.source.remote.model.SeasonRes
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.usecase.CatalogueUseCase

class DetailTvShowViewModel(private val catalogueUseCase: CatalogueUseCase) : ViewModel() {
    private val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    fun setFavorite(tvShow: TvShow, newStatus:Boolean) = catalogueUseCase.setTvShowFavorite(tvShow, newStatus)

    fun getSeasons(): LiveData<List<SeasonRes>> {
        return catalogueUseCase.getSeasonsByTvShow(this.tvShowId.value!!)
    }
}