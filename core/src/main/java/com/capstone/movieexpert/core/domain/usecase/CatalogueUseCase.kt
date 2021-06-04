package com.capstone.movieexpert.core.domain.usecase

import com.capstone.movieexpert.core.data.source.Resource
import com.capstone.movieexpert.core.domain.model.Movie
import com.capstone.movieexpert.core.domain.model.TvShow
import kotlinx.coroutines.flow.Flow

interface CatalogueUseCase {
    fun getMovies(page: Int): Flow<Resource<List<Movie>>>
    fun getMovie(id: Int): Flow<Resource<Movie>>
    fun getFavoriteMovies(): Flow<List<Movie>>
    fun setMovieFavorite(movie: Movie, state: Boolean)

    fun getTvShows(page: Int): Flow<Resource<List<TvShow>>>
    fun getTvShow(id: Int): Flow<Resource<TvShow>>
    fun getFavoriteTvShows(): Flow<List<TvShow>>
    fun setTvShowFavorite(tvShow: TvShow, state: Boolean)
}