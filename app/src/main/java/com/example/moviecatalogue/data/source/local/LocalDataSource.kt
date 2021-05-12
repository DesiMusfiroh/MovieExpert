package com.example.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getMovies()

    fun getMovie(movieId: Int): LiveData<MovieEntity> =
        mCatalogueDao.getMovie(movieId)

    fun getFavoriteMovies(): DataSource.Factory<Int, MovieEntity> = mCatalogueDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogueDao.updateMovie(movie)
    }


    fun getTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getTvShows()

    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity> =
            mCatalogueDao.getTvShow(tvShowId)

    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity> = mCatalogueDao.getFavoriteTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCatalogueDao.updateTvShow(tvShow)
    }

}