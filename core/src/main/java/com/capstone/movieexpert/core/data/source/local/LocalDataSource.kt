package com.capstone.movieexpert.core.data.source.local

import com.capstone.movieexpert.core.data.source.local.entity.MovieEntity
import com.capstone.movieexpert.core.data.source.local.entity.TvShowEntity
import com.capstone.movieexpert.core.data.source.local.room.CatalogueDao
import kotlinx.coroutines.flow.Flow

class LocalDataSource (private val mCatalogueDao: CatalogueDao) {

    fun getMovies(): Flow<List<MovieEntity>> = mCatalogueDao.getMovies()

    fun getMovie(movieId: Int): Flow<MovieEntity> = mCatalogueDao.getMovie(movieId)

    fun getFavoriteMovies(): Flow<List<MovieEntity>> = mCatalogueDao.getFavoriteMovies()

    suspend fun insertMovies(movies: List<MovieEntity>) = mCatalogueDao.insertMovies(movies)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorited = newState
        mCatalogueDao.updateMovie(movie)
    }


    fun getTvShows(): Flow<List<TvShowEntity>> = mCatalogueDao.getTvShows()

    fun getTvShow(tvShowId: Int): Flow<TvShowEntity> = mCatalogueDao.getTvShow(tvShowId)

    fun getFavoriteTvShows(): Flow<List<TvShowEntity>> = mCatalogueDao.getFavoriteTvShows()

    suspend fun insertTvShows(tvShows: List<TvShowEntity>) = mCatalogueDao.insertTvShows(tvShows)

    fun setTvShowFavorite(tvShow: TvShowEntity, newState: Boolean) {
        tvShow.favorited = newState
        mCatalogueDao.updateTvShow(tvShow)
    }

}