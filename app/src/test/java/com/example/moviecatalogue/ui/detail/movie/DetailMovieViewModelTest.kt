package com.example.moviecatalogue.ui.detail.movie

import com.example.moviecatalogue.utils.DataDummy
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel()
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        viewModel.setSelectedMovie(dummyMovie.id)
        val movieEntity = viewModel.getMovie()
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.name, movieEntity.name)
        assertEquals(dummyMovie.desc, movieEntity.desc)
        assertEquals(dummyMovie.poster, movieEntity.poster)
        assertEquals(dummyMovie.backdrop, movieEntity.backdrop)
        assertEquals(dummyMovie.date, movieEntity.date)
        assertEquals(dummyMovie.rating, movieEntity.rating, 0.001)
        assertEquals(dummyMovie.popularity, movieEntity.popularity, 0.001)
    }
}