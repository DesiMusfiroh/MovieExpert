package com.example.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.moviecatalogue.data.source.CatalogueRepository
import com.example.moviecatalogue.di.Injection
import com.example.moviecatalogue.ui.home.movie.MovieViewModel
import com.example.moviecatalogue.ui.home.tvshow.TvShowViewModel

class ViewModelFactory private constructor(private val mCatalogueRepository: CatalogueRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TvShowViewModel::class.java) -> {
                return TvShowViewModel(mCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }
}