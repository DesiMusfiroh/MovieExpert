package com.made.movieexpert.ui.detail.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.utils.DataDummy
import com.made.movieexpert.vo.Resource
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DetailTvShowViewModelTest {
    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = Resource.success(DataDummy.generateDummyTvShows()[0])
    private val tvShowId = dummyTvShow.data?.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<TvShowEntity>>

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel(catalogueRepository)
        viewModel.setSelectedTvShow(tvShowId!!)
    }

    @Test
    fun getTvShow() {
        val dummyTvShow = Resource.success(DataDummy.generateDummyTvShow())
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(catalogueRepository.getTvShow(tvShowId!!)).thenReturn(tvShow)

        viewModel.getTvShow.observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

    @Test
    fun setFavoriteTvShow() {
        val dummyTvShow = Resource.success(DataDummy.generateDummyTvShows()[0])
        val tvShow = MutableLiveData<Resource<TvShowEntity>>()
        tvShow.value = dummyTvShow

        `when`(catalogueRepository.getTvShow(tvShowId!!)).thenReturn(tvShow)
        viewModel.getTvShow.observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
        viewModel.setFavorite()
        verify(catalogueRepository).setTvShowFavorite(tvShow.value!!.data as TvShowEntity, true)
        verifyNoMoreInteractions(observer)
    }
}