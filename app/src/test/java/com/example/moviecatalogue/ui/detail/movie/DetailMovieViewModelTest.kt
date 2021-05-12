package com.example.moviecatalogue.ui.detail.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.moviecatalogue.vo.Resource
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.utils.DataDummy
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
class DetailMovieViewModelTest {
    private lateinit var viewModel: DetailMovieViewModel
    private val dummyMovie = Resource.success(DataDummy.generateDummyMovies()[0])
    private val movieId = dummyMovie.data?.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var catalogueRepository: CatalogueRepository

    @Mock
    private lateinit var observer: Observer<Resource<MovieEntity>>

    @Before
    fun setUp() {
        viewModel = DetailMovieViewModel(catalogueRepository)
        viewModel.setSelectedMovie(movieId!!)
    }

    @Test
    fun getMovie() {
        val dummyMovie = Resource.success(DataDummy.generateDummyMovie())
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(catalogueRepository.getMovie(movieId!!)).thenReturn(movie)

        viewModel.getMovie.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun setFavoriteMovie() {
        val dummyMovie = Resource.success(DataDummy.generateDummyMovies()[0])
        val movie = MutableLiveData<Resource<MovieEntity>>()
        movie.value = dummyMovie

        `when`(catalogueRepository.getMovie(movieId!!)).thenReturn(movie)
        viewModel.getMovie.observeForever(observer)
        verify(observer).onChanged(dummyMovie)
        viewModel.setFavorite()
        verify(catalogueRepository).setMovieFavorite(movie.value!!.data as MovieEntity, true)
        verifyNoMoreInteractions(observer)
    }
}