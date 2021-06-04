package com.made.movieexpert.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.made.movieexpert.data.source.remote.network.ApiResponse
import com.made.movieexpert.data.source.remote.response.SeasonResponse
import com.made.movieexpert.data.source.local.LocalDataSource
import com.made.movieexpert.data.source.remote.RemoteDataSource
import com.made.movieexpert.data.source.remote.response.MovieResponse
import com.made.movieexpert.data.source.remote.response.TvShowResponse
import com.made.movieexpert.utils.AppExecutors

class FakeCatalogueRepository constructor(
        private val remoteDataSource: RemoteDataSource,
        private val localDataSource: LocalDataSource,
        private val appExecutors: AppExecutors
) : CatalogueDataSource {

    override fun getMovies(page: Int): LiveData<Resource<PagedList<MovieEntity>>> {
        return object : NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> = remoteDataSource.getMovies(1)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
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
        return object : NetworkBoundResource<MovieEntity, MovieResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<MovieEntity> = localDataSource.getMovie(id)

            override fun shouldFetch(data: MovieEntity?): Boolean = data == null

            override suspend fun createCall(): LiveData<ApiResponse<MovieResponse>> = remoteDataSource.getMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                localDataSource.getMovie(id)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovies(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }


    override fun getTvShows(page: Int): LiveData<Resource<PagedList<TvShowEntity>>> {
        return object : NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TvShowEntity>> {
                val config = PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build()
                return LivePagedListBuilder(localDataSource.getTvShows(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShowEntity>?): Boolean = data == null || data.isEmpty()

            override suspend fun createCall(): LiveData<ApiResponse<List<TvShowResponse>>> = remoteDataSource.getTvShows(1)

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
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
        return object : NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShowEntity> = localDataSource.getTvShow(id)

            override fun shouldFetch(data: TvShowEntity?): Boolean = data == null

            override suspend fun createCall(): LiveData<ApiResponse<TvShowResponse>> = remoteDataSource.getTvShow(id)

            override suspend fun saveCallResult(data: TvShowResponse) {
                localDataSource.getTvShow(id)
            }

        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShowEntity>> {
        val config = PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTvShows(), config).build()
    }

    override fun setTvShowFavorite(tvShow: TvShowEntity, state: Boolean) =
            appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShow, state) }

    override fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<SeasonResponse>> {
        return remoteDataSource.getSeasonsByTvShow(tvShowId)
    }

}
