package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val catalogueRepository = FakeCatalogueRepository(remote)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val tvShowResponses = DataDummy.generateDummyTvShows()
    private val movieId = movieResponses[0].id
    private val tvShowId = tvShowResponses[0].id
    private val movieResponse = DataDummy.generateDummyMovie()
    private val tvShowResponse = DataDummy.generateDummyTvShow()
    private val seasonResponses = DataDummy.generateDummySeasons()

    @Test
    fun getMovies() {
        val movies = MutableLiveData<List<Movie>>()
        movies.value = movieResponses

        `when`(remote.getMovies(1)).thenReturn(movies)
        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getMovies(1))
        verify(remote).getMovies(1)
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getTvShows() {
        val tvShow = MutableLiveData<List<TvShow>>()
        tvShow.value = tvShowResponses

        `when`(remote.getTvShows(1)).thenReturn(tvShow)
        val tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getTvShows(1))
        verify(remote).getTvShows(1)
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<Movie>()
        movie.value = movieResponse

        `when`(remote.getMovie(movieId)).thenReturn(movie)
        val resultMovie = catalogueRepository.getMovie(movieId)
        verify(remote).getMovie(movieId)
        assertNotNull(resultMovie)
        assertEquals(movieResponse.name, resultMovie.value?.name)
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<TvShow>()
        tvShow.value = tvShowResponse

        `when`(remote.getTvShow(tvShowId)).thenReturn(tvShow)
        val resultTvShow = catalogueRepository.getTvShow(tvShowId)
        verify(remote).getTvShow(tvShowId)
        assertNotNull(resultTvShow)
        assertEquals(tvShowResponse.name, resultTvShow.value?.name)
    }

    @Test
    fun getSeasonsByTvShow() {
        val season = MutableLiveData<List<Season>>()
        season.value = seasonResponses

        `when`(remote.getSeasonsByTvShow()).thenReturn(season)
        val seasonEntities = LiveDataTestUtil.getValue(catalogueRepository.getSeasonsByTvShow(tvShowId))
        verify(remote).getSeasonsByTvShow()
        assertNotNull(seasonEntities)
        assertEquals(seasonResponses.size.toLong(), seasonEntities.size.toLong())
    }
}