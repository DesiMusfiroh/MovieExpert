package com.example.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.vo.Resource

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

    fun getSeasons(): LiveData<List<Season>> {
        return catalogueRepository.getSeasonsByTvShow(this.tvShowId.value!!)
    }


}