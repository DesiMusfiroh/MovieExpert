package com.example.moviecatalogue.ui.detail.tvshow

import androidx.lifecycle.ViewModel
import com.example.moviecatalogue.data.CatalogueEntity
import com.example.moviecatalogue.utils.DataDummy

class DetailTvShowViewModel : ViewModel() {
    private var tvShowId : Int = 0

    private fun getListTvShow(): ArrayList<CatalogueEntity> = DataDummy.generateDummyTvShows() as ArrayList<CatalogueEntity>

    fun setSelectedTvShow(tvShowId: Int) {
        this.tvShowId = tvShowId
    }

    fun getTvShow(): CatalogueEntity {
        lateinit var result: CatalogueEntity
        val listTvShow = getListTvShow()
        for (tvShowEntity in listTvShow) {
            if (tvShowEntity.id == tvShowId) result = tvShowEntity
        }
        return result
    }
}