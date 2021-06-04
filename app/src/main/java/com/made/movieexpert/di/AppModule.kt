package com.made.movieexpert.di

import com.capstone.movieexpert.core.domain.usecase.CatalogueInteractor
import com.capstone.movieexpert.core.domain.usecase.CatalogueUseCase
import com.made.movieexpert.ui.detail.movie.DetailMovieViewModel
import com.made.movieexpert.ui.detail.tvshow.DetailTvShowViewModel
import com.made.movieexpert.ui.favorite.movie.FavoriteMovieViewModel
import com.made.movieexpert.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.made.movieexpert.ui.home.movie.MovieViewModel
import com.made.movieexpert.ui.home.tvshow.TvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CatalogueUseCase> { CatalogueInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MovieViewModel(get()) }
    viewModel { TvShowViewModel(get()) }
    viewModel { DetailMovieViewModel(get()) }
    viewModel { DetailTvShowViewModel(get()) }
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}