package com.made.movieexpert.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.made.movieexpert.data.source.remote.model.SeasonRes
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.vo.Resource

interface CatalogueDataSource {
    fun getMovies(page: Int): LiveData<Resource<List<Movie>>>
    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<List<Movie>>
    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getTvShows(page: Int): LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<SeasonRes>>
}