package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.api.ApiResponse
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getMovies(page: Int): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localDataSource.getMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> = remoteDataSource.getMovies(1)

            override fun saveCallResult(movieResponses: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
                    val movie = MovieEntity(
                        response.id,
                        response.name,
                        response.desc,
                        response.poster,
                        response.backdrop,
                        response.date,
                        response.popularity,
                        response.rating,
                        false
                    )
                    movieList.add(movie)
                }
                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }

    override fun getTvShows(page: Int): LiveData<List<TvShow>> {
        return remoteDataSource.getTvShows(page)
    }

    override fun getMovie(id: Int): LiveData<Movie> {
        return remoteDataSource.getMovie(id)
    }

    override fun getTvShow(id: Int): LiveData<TvShow> {
        return remoteDataSource.getTvShow(id)
    }

    override fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<Season>> {
        return remoteDataSource.getSeasonsByTvShow()
    }
}