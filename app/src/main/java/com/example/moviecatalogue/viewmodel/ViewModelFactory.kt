package com.example.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.ui.detail.movie.DetailMovieViewModel
import com.example.moviecatalogue.ui.detail.tvshow.DetailTvShowViewModel
import com.example.moviecatalogue.ui.favorite.movie.FavoriteMovieViewModel
import com.example.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowFragment
import com.example.moviecatalogue.ui.favorite.tvshow.FavoriteTvShowViewModel
import com.example.moviecatalogue.ui.home.movie.MovieViewModel
import com.example.moviecatalogue.ui.home.tvshow.TvShowViewModel

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