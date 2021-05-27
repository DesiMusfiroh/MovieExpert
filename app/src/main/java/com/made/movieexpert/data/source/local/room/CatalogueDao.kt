package com.made.movieexpert.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies():  DataSource.Factory<Int, MovieEntity>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movieentities where favorited = 1")
    fun getFavoriteMovies():  DataSource.Factory<Int, MovieEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)


    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentities where favorited = 1")
    fun getFavoriteTvShows(): DataSource.Factory<Int, TvShowEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(movie: TvShowEntity)

}