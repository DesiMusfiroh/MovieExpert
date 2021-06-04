package com.made.movieexpert.data.source.local

import androidx.lifecycle.LiveData
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.data.source.local.room.CatalogueDao

class LocalDataSource private constructor(private val mCatalogueDao: CatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(catalogueDao: CatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(catalogueDao)
    }

    fun getMovies(): LiveData<List<MovieEntity>> = mCatalogueDao.getMovies()

    fun getMovie(movieId: Int): LiveData<MovieEntity> = mCatalogueDao.getMovie(movieId)

    fun getFavoriteMovies(): LiveData<List<MovieEntity>> = mCatalogueDao.getFavoriteMovies()

    fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogueDao.updateMovie(movie)
    }

    fun getTvShows(): LiveData<List<TvShowEntity>>  = mCatalogueDao.getTvShows()

    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity> = mCatalogueDao.getTvShow(tvShowId)

    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> = mCatalogueDao.getFavoriteTvShows()

    fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCatalogueDao.updateTvShow(tvShow)
    }

}