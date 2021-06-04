package com.capstone.movieexpert.core.data.source.local.room

import androidx.room.*
import com.capstone.movieexpert.core.data.source.local.entity.MovieEntity
import com.capstone.movieexpert.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movies")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun getMovie(movieId: Int): Flow<MovieEntity>

    @Query("SELECT * FROM movies where favorited = 1")
    fun getFavoriteMovies():  Flow<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)


    @Query("SELECT * FROM tv_shows")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM tv_shows WHERE tvShowId = :tvShowId")
    fun getTvShow(tvShowId: Int): Flow<TvShowEntity>

    @Query("SELECT * FROM tv_shows where favorited = 1")
    fun getFavoriteTvShows(): Flow<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(movie: TvShowEntity)

}