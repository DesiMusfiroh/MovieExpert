package com.made.movieexpert.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.made.movieexpert.data.source.CatalogueRepository
import com.made.movieexpert.di.Injection
import com.made.movieexpert.ui.detail.movie.DetailMovieViewModel
import com.made.movieexpert.ui.detail.tvshow.DetailTvShowViewModel
import com.made.movieexpert.ui.favorite.movie.FavoriteMovieViewModel
import com.made.movieexpert.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.made.movieexpert.ui.home.movie.MovieViewModel
import com.made.movieexpert.ui.home.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogueRepository: CatalogueRepository)
    : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                ViewModelFactory(Injection.provideRepository(context)).apply { instance = this }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                TvShowViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailMovieViewModel::class.java) -> {
                DetailMovieViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailTvShowViewModel::class.java) -> {
                DetailTvShowViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteMovieViewModel::class.java) -> {
                FavoriteMovieViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(FavoriteTvShowViewModel::class.java) -> {
                FavoriteTvShowViewModel(mCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}