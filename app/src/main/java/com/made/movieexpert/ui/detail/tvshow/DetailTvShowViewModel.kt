package com.made.movieexpert.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.made.movieexpert.data.model.SeasonRes
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.vo.Resource

class DetailTvShowViewModel(private val catalogueRepository: CatalogueRepository) : ViewModel() {
    val tvShowId = MutableLiveData<Int>()

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId.value = tvShowId
    }

    var getTvShow: LiveData<Resource<TvShowEntity>> = Transformations.switchMap(tvShowId) { mTvShowId ->
        catalogueRepository.getTvShow(mTvShowId)
    }

    fun setFavorite() {
        val tvShowResource = getTvShow.value
        val tvShowEntity = tvShowResource?.data
        val newState = !tvShowEntity?.favorited!!
        catalogueRepository.setTvShowFavorite(tvShowEntity, newState)
    }

    fun getSeasons(): LiveData<List<SeasonRes>> {
        return catalogueRepository.getSeasonsByTvShow(this.tvShowId.value!!)
    }


}