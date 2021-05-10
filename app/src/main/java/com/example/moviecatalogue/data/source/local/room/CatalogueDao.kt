package com.example.moviecatalogue.data.source.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.SeasonEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity

@Dao
interface CatalogueDao {

    @Query("SELECT * FROM movieentities")
    fun getMovies(): LiveData<List<MovieEntity>>

    @Query("SELECT * FROM movieentities WHERE movieId = :movieId")
    fun getMovie(movieId: Int): LiveData<MovieEntity>

    @Query("SELECT * FROM movieentities where favorited = 1")
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovies(movies: List<MovieEntity>)

    @Update
    fun updateMovie(movie: MovieEntity)


    @Query("SELECT * FROM tvshowentities")
    fun getTvShows(): LiveData<List<TvShowEntity>>

    @Query("SELECT * FROM tvshowentities WHERE tvShowId = :tvShowId")
    fun getTvShow(tvShowId: Int): LiveData<TvShowEntity>

    @Query("SELECT * FROM tvshowentities where favorited = 1")
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShows(movies: List<TvShowEntity>)

    @Update
    fun updateTvShow(movie: TvShowEntity)


//    @Query("SELECT * FROM seasonentities WHERE tvShowId = :tvShowId")
//    fun getSeasonByTvShowId(tvShowId: Int): LiveData<List<SeasonEntity>>
//
//    @Query("SELECT * FROM seasonentities WHERE seasonId = :seasonId")
//    fun getSeason(seasonId: Int): LiveData<SeasonEntity>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertSeasons(module: List<SeasonEntity>)
}