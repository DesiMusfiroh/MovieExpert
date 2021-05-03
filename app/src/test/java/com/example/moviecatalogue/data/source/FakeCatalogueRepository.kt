package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.remote.RemoteDataSource

class FakeCatalogueRepository(private val remoteDataSource: RemoteDataSource) : CatalogueDataSource {

    override fun getMovies(page: Int): LiveData<List<Movie>> {
        return remoteDataSource.getMovies(page)
    }

    override fun getTvShows(page: Int): LiveData<List<TvShow>> {
        return remoteDataSource.getTvShows(page)
    }

    override fun getMovie(id: Int): LiveData<Movie> {
        return remoteDataSource.getMovie(id)
    }

    override fun getTvShow(id: Int): LiveData<TvShow> {
        return remoteDataSource.getTvShow(id,)
    }

    override fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<Season>> {
        return remoteDataSource.getSeasonsByTvShow()
    }
}