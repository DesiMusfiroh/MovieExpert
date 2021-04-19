package com.example.moviecatalogue.ui.detail.tvshow

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DetailTvShowViewModelTest {

    private lateinit var viewModel: DetailTvShowViewModel
    private val dummyTvShow = DataDummy.generateDummyTvShows()[0]
    private val tvShowId = dummyTvShow.id

    @Before
    fun setUp() {
        viewModel = DetailTvShowViewModel()
        viewModel.setSelectedTvShow(tvShowId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedTvShow(dummyTvShow.id)
        val tvShowEntity = viewModel.getTvShow()
        assertNotNull(tvShowEntity)
        assertEquals(dummyTvShow.id, tvShowEntity.id)
        assertEquals(dummyTvShow.name, tvShowEntity.name)
        assertEquals(dummyTvShow.desc, tvShowEntity.desc)
        assertEquals(dummyTvShow.poster, tvShowEntity.poster)
        assertEquals(dummyTvShow.backdrop, tvShowEntity.backdrop)
        assertEquals(dummyTvShow.date, tvShowEntity.date)
        assertEquals(dummyTvShow.rating, tvShowEntity.rating, 0.001)
        assertEquals(dummyTvShow.popularity, tvShowEntity.popularity, 0.001)
    }
}