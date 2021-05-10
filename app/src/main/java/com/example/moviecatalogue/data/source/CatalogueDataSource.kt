package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.vo.Resource

interface CatalogueDataSource {
    fun getMovies(page: Int): LiveData<Resource<List<MovieEntity>>>
    fun getMovie(id: Int): LiveData<Resource<MovieEntity>>
    fun getFavoriteMovies(): LiveData<List<MovieEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)

    fun getTvShows(page: Int): LiveData<Resource<List<TvShowEntity>>>
    fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>>
    fun getFavoriteTvShows(): LiveData<List<TvShowEntity>>
    fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean)

    fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<Season>>
}