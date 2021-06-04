package com.made.movieexpert.domain.repository

import androidx.lifecycle.LiveData
import com.made.movieexpert.data.source.remote.model.SeasonRes
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.vo.Resource

interface ICatalogueRepository {
    fun getMovies(page: Int): LiveData<Resource<List<Movie>>>
    fun getMovie(id: Int): LiveData<Resource<Movie>>
    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getTvShows(page: Int): LiveData<Resource<List<TvShow>>>
    fun getTvShow(id: Int): LiveData<Resource<TvShow>>
    fun getFavoriteTvShows(): LiveData<List<TvShow>>
    fun setTvShowFavorite(tvShow: TvShow, state: Boolean)

    fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<SeasonRes>>
}