package com.made.movieexpert.favorite

import com.made.movieexpert.favorite.movie.FavoriteMovieViewModel
import com.made.movieexpert.favorite.tvshow.FavoriteTvShowViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val favoriteModule = module {
    viewModel { FavoriteMovieViewModel(get()) }
    viewModel { FavoriteTvShowViewModel(get()) }
}