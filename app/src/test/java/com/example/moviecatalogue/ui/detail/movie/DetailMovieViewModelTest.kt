package com.example.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.data.model.Movie
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val movieId = dummyMovie.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Movie>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
        viewModel.setSelectedMovie(movieId)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<Movie>()
        movie.value = dummyMovie

        `when`(catalogueRepository.getMovie(movieId)).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value as Movie
        verify(catalogueRepository).getMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.id, movieEntity.id)
        assertEquals(dummyMovie.name, movieEntity.name)
        assertEquals(dummyMovie.desc, movieEntity.desc)
        assertEquals(dummyMovie.poster, movieEntity.poster)
        assertEquals(dummyMovie.backdrop, movieEntity.backdrop)
        assertEquals(dummyMovie.date, movieEntity.date)
        assertEquals(dummyMovie.rating, movieEntity.rating, 0.001)
        assertEquals(dummyMovie.popularity, movieEntity.popularity, 0.001)

        viewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }
}