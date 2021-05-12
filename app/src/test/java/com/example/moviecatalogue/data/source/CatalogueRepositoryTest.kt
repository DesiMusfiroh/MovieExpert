package com.example.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.room.CatalogueDao
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.utils.DataDummy
import com.example.moviecatalogue.utils.LiveDataTestUtil
import com.example.moviecatalogue.utils.PagedListUtil
import com.example.moviecatalogue.vo.Resource
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.*
import java.util.concurrent.Executors

class CatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val dao = mock(CatalogueDao::class.java)
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
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getMovies(1)

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getTvShows(1)

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
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
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, MovieEntity>
        `when`(local.getFavoriteMovies()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteMovies()

        val movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()))
        verify(local).getFavoriteMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShowEntity>
        `when`(local.getFavoriteTvShows()).thenReturn(dataSourceFactory)
        catalogueRepository.getFavoriteTvShows()

        val tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShows()))
        verify(local).getFavoriteTvShows()
        assertNotNull(tvShowEntities)
        assertEquals(tvShowResponses.size.toLong(), tvShowEntities.data?.size?.toLong())
    }

    @Test
    fun setMovieFavorite() {
        val localData = LocalDataSource.getInstance(dao)
        val dataDummy = DataDummy.generateDummyMovies()[0]
        val expectedDataDummy = dataDummy.copy(favorited = true)

        doNothing().`when`(dao).updateMovie(expectedDataDummy)
        localData.setMovieFavorite(dataDummy, true)
        verify(dao, times(1)).updateMovie(expectedDataDummy)
    }

    @Test
    fun setTvShowFavorite() {
        val dataDummy = DataDummy.generateDummyTvShows()[0]
        val newState = !dataDummy.favorited

        `when`(appExecutors.diskIO()).thenReturn(Executors.newSingleThreadExecutor())
        local.setTvShowFavorite(dataDummy, newState)

        catalogueRepository.setTvShowFavorite(dataDummy, newState)
        verify(local, times(1)).setTvShowFavorite(dataDummy, newState)
        verifyNoMoreInteractions(local)
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