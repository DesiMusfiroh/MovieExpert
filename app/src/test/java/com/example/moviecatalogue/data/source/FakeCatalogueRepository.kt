package com.example.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.example.moviecatalogue.api.ApiResponse
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.local.LocalDataSource
import com.example.moviecatalogue.data.source.local.entity.MovieEntity
import com.example.moviecatalogue.data.source.local.entity.TvShowEntity
import com.example.moviecatalogue.data.source.remote.RemoteDataSource
import com.example.moviecatalogue.utils.AppExecutors
import com.example.moviecatalogue.vo.Resource

class FakeCatalogueRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : CatalogueDataSource {

    override fun getMovies(page: Int): LiveData<Resource<List<MovieEntity>>> {
        return object : NetworkBoundResource<List<MovieEntity>, List<Movie>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<MovieEntity>> = localDataSource.getMovies()

            override fun shouldFetch(data: List<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movie>>> = remoteDataSource.getMovies(1)

            override fun saveCallResult(data: List<Movie>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in data) {
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

    override fun getMovie(id: Int): LiveData<Resource<MovieEntity>> {
        return object : NetworkBoundResource<MovieEntity, Movie>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovie(id)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<Movie>> = remoteDataSource.getMovie(id)

            override fun saveCallResult(data: Movie) {
                localDataSource.getMovie(id)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<List<MovieEntity>> = localDataSource.getFavoriteMovies()

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }


    override fun getTvShows(page: Int): LiveData<Resource<List<TvShowEntity>>> {
        return object : NetworkBoundResource<List<TvShowEntity>, List<TvShow>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShowEntity>> = localDataSource.getTvShows()

            override fun shouldFetch(data: List<TvShowEntity>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShow>>> = remoteDataSource.getTvShows(1)

            override fun saveCallResult(data: List<TvShow>) {
                val tvShowList = ArrayList<TvShowEntity>()
                for (response in data) {
                    val tvShow = TvShowEntity(
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
                    tvShowList.add(tvShow)
                }
                localDataSource.insertTvShows(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShow(id: Int): LiveData<Resource<TvShowEntity>> {
        return object : NetworkBoundResource<TvShowEntity, TvShow>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShow(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvShow>> = remoteDataSource.getTvShow(id)

            override fun saveCallResult(data: TvShow) {
                localDataSource.getTvShow(id)
            }

        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<List<TvShowEntity>> = localDataSource.getFavoriteTvShows()

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }

    override fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<Season>> {
        return remoteDataSource.getSeasonsByTvShow(tvShowId)
    }

}