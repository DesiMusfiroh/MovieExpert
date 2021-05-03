package com.example.moviecatalogue.ui.home.tvshow

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowViewModelTest {

    private lateinit var viewModel: TvShowViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TvShow>>

    @Before
    fun setUp() {
        viewModel = TvShowViewModel(catalogueRepository)
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = DataDummy.generateDummyTvShows()
        val tvShows = MutableLiveData<List<TvShow>>()
        tvShows.value = dummyTvShows

        `when`(catalogueRepository.getTvShows(1)).thenReturn(tvShows)
        val tvShowsEntities = viewModel.getTvShows().value
        verify(catalogueRepository).getTvShows(1)
        assertNotNull(tvShowsEntities)
        assertEquals(12, tvShowsEntities?.size)

        viewModel.getTvShows().observeForever(observer)
        verify(observer).onChanged(dummyTvShows)
    }
}