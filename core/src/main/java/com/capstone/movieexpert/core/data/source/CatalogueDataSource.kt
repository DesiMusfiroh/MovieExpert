package com.capstone.movieexpert.core.data.source

import androidx.lifecycle.LiveData
import com.capstone.movieexpert.core.data.source.local.entity.MovieEntity
import com.capstone.movieexpert.core.data.source.local.entity.TvShowEntity
import com.capstone.movieexpert.core.domain.model.Movie

interface CatalogueDataSource {
    fun getMovies(page: Int): LiveData<Resource<List<Movie>>>
    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getTvShows(page: Int): LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)
}