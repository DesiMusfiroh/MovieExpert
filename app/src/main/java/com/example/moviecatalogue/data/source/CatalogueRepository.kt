package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class CatalogueRepository private constructor(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData).apply { instance = this }
            }
    }

    override fun getMovies(page: Int): LiveData<List<Movie>> {
        return remoteDataSource.getMovies(page)
    }

    override fun getTvShows(page: Int): LiveData<List<TvShow>> {
        return remoteDataSource.getTvShows(page)
    }
}