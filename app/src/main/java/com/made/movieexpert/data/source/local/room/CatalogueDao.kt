package com.made.movieexpert.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movies where favorited = 1")
    fun getFavoriteMovies():  LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)


    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows WHERE tvShowId = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tv_shows where favorited = 1")
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(movie: TvShowEntity)

}