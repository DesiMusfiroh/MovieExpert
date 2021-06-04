package com.made.movieexpert.domain.usecase

import com.made.movieexpert.data.source.Resource
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.repository.ICatalogueRepository
import kotlinx.coroutines.flow.Flow

class CatalogueInteractor(private val hiBykesRepository: ICatalogueRepository): CatalogueUseCase {
    override fun getMovies(page: Int): Flow<Resource<List<Movie>>> = hiBykesRepository.getMovies(page)
    override fun getMovie(id: Int): Flow<Resource<Movie>> = hiBykesRepository.getMovie(id)
    override fun getFavoriteMovies(): Flow<List<Movie>> = hiBykesRepository.getFavoriteMovies()
    override fun setMovieFavorite(movie: Movie, state: Boolean) = hiBykesRepository.setMovieFavorite(movie, state)

    override fun getTvShows(page: Int): Flow<Resource<List<TvShow>>> = hiBykesRepository.getTvShows(page)
    override fun getTvShow(id: Int): Flow<Resource<TvShow>> = hiBykesRepository.getTvShow(id)
    override fun getFavoriteTvShows(): Flow<List<TvShow>> = hiBykesRepository.getFavoriteTvShows()
    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) = hiBykesRepository.setTvShowFavorite(tvShow, state)
}