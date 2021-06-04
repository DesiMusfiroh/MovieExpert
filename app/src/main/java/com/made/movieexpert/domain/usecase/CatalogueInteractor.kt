package com.made.movieexpert.domain.usecase

import androidx.lifecycle.LiveData
import com.made.movieexpert.data.source.remote.model.SeasonRes
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.repository.ICatalogueRepository
import com.made.movieexpert.vo.Resource

class CatalogueInteractor(private val hiBykesRepository: ICatalogueRepository): CatalogueUseCase {

    override fun getMovies(page: Int): LiveData<Resource<List<Movie>>> = hiBykesRepository.getMovies(page)
    override fun getMovie(id: Int): LiveData<Resource<Movie>> = hiBykesRepository.getMovie(id)
    override fun getFavoriteMovies(): LiveData<List<Movie>> = hiBykesRepository.getFavoriteMovies()
    override fun setMovieFavorite(movie: Movie, state: Boolean)  = hiBykesRepository.setMovieFavorite(movie, state)

    override fun getTvShows(page: Int): LiveData<Resource<List<TvShow>>> = hiBykesRepository.getTvShows(page)
    override fun getTvShow(id: Int): LiveData<Resource<TvShow>> = hiBykesRepository.getTvShow(id)
    override fun getFavoriteTvShows(): LiveData<List<TvShow>> = hiBykesRepository.getFavoriteTvShows()
    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) = hiBykesRepository.setTvShowFavorite(tvShow, state)

    override fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<SeasonRes>> = hiBykesRepository.getSeasonsByTvShow(tvShowId)
}