package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
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
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val catalogueRepository = FakeCatalogueRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateDummyMovies()
    private val tvShowResponses = DataDummy.generateDummyTvShows()
    private val movieId = movieResponses[0].id
    private val tvShowId = tvShowResponses[0].id
    private val movieResponse = DataDummy.generateDummyMovie()
    private val tvShowResponse = DataDummy.generateDummyTvShow()
    private val seasonResponses = DataDummy.generateDummySeasons()

    @Test
    fun getMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getMovies(1))
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getTvShows()).thenReturn(dummyTvShows)

        val tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getTvShows(1))
        verify(local).getTvShows()
        assertNotNull(tvShowEntities.data)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun getMovie() {
        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovie()
        `when`(local.getMovie(movieId)).thenReturn(dummyEntity)

        val resultMovie = LiveDataTestUtil.getValue(catalogueRepository.getMovie(movieId))
        verify(local).getMovie(movieId)
        assertNotNull(resultMovie.data)
        assertEquals(movieResponse.name, resultMovie.data?.name)
    }

    @Test
    fun getTvShow() {
        val dummyEntity = MutableLiveData<TvShowEntity>()
        dummyEntity.value = DataDummy.generateDummyTvShow()
        `when`(local.getTvShow(tvShowId)).thenReturn(dummyEntity)

        val resultTvShow = LiveDataTestUtil.getValue(catalogueRepository.getTvShow(tvShowId))
        verify(local).getTvShow(tvShowId)
        assertNotNull(resultTvShow)
        assertEquals(tvShowResponse.name, resultTvShow.data?.name)
    }

    @Test
    fun getFavoriteMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getFavoriteMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(catalogueRepository.getFavoriteMovies())
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dummyTvShows = MutableLiveData<List<TvShowEntity>>()
        dummyTvShows.value = DataDummy.generateDummyTvShows()
        `when`(local.getFavoriteTvShows()).thenReturn(dummyTvShows)

        val tvShowEntities = LiveDataTestUtil.getValue(catalogueRepository.getFavoriteTvShows())
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.size.toLong())
    }

    @Test
    fun getSeasonsByTvShow() {
        val season = MutableLiveData<List<Season>>()
        season.value = seasonResponses

        `when`(remote.getSeasonsByTvShow(tvShowId)).thenReturn(season)
        val seasonEntities = LiveDataTestUtil.getValue(catalogueRepository.getSeasonsByTvShow(tvShowId))
        verify(remote).getSeasonsByTvShow(tvShowId)
        assertNotNull(seasonEntities)
        assertEquals(seasonResponses.size.toLong(), seasonEntities.size.toLong())
    }
}