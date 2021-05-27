@file:Suppress("DEPRECATION")

package com.made.movieexpert.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.made.movieexpert.data.model.SeasonRes
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.vo.Resource

interface CatalogueDataSource {
    fun getMovies(page: Int): LiveData<Resource<PagedList<MovieEntity>>>
    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getTvShows(page: Int): LiveData<Resource<PagedList<TvShowEntity>>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>>
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<SeasonRes>>
}