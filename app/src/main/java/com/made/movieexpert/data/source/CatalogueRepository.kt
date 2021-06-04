package com.made.movieexpert.data.source

import com.made.movieexpert.data.source.remote.network.ApiResponse
import com.made.movieexpert.data.source.local.LocalDataSource
import com.made.movieexpert.data.source.remote.RemoteDataSource
import com.made.movieexpert.data.source.remote.response.MovieResponse
import com.made.movieexpert.data.source.remote.response.TvShowResponse
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.repository.ICatalogueRepository
import com.made.movieexpert.utils.AppExecutors
import com.made.movieexpert.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

@Suppress("UNCHECKED_CAST")
class CatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : ICatalogueRepository {

    companion object {
        @Volatile
        private var instance: CatalogueRepository? = null
        fun getInstance(remoteData: RemoteDataSource, localData: LocalDataSource, appExecutors: AppExecutors): CatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: CatalogueRepository(remoteData, localData, appExecutors).apply { instance = this }
            }
    }

    override fun getMovies(page: Int): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>() {
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getMovies().map {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getMovies(page)

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapMovieResponsesToEntities(data)
                localDataSource.insertMovies(movieList)
            }
        }.asFlow()


    override fun getMovie(id: Int): Flow<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieResponse>() {
            override fun loadFromDB(): Flow<Movie> {
                return localDataSource.getMovie(id) as Flow<Movie>
            }

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<MovieResponse>> = remoteDataSource.getMovie(id)

            override suspend fun saveCallResult(data: MovieResponse) {
                localDataSource.getMovie(id)
            }

        }.asFlow()
    }

    override fun getFavoriteMovies(): Flow<List<Movie>> {
        return localDataSource.getFavoriteMovies().map {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }



    override fun getTvShows(page: Int): Flow<Resource<List<TvShow>>> =
        object : NetworkBoundResource<List<TvShow>, List<TvShowResponse>>() {
            override fun loadFromDB(): Flow<List<TvShow>> {
                return localDataSource.getTvShows().map {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean =
                data == null || data.isEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<TvShowResponse>>> =
                remoteDataSource.getTvShows(page)

            override suspend fun saveCallResult(data: List<TvShowResponse>) {
                val tvShowList = DataMapper.mapTvShowResponsesToEntities(data)
                localDataSource.insertTvShows(tvShowList)
            }
        }.asFlow()

    override fun getTvShow(id: Int): Flow<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowResponse>() {
            override fun loadFromDB(): Flow<TvShow> = localDataSource.getTvShow(id) as Flow<TvShow>

            override fun shouldFetch(data: TvShow?): Boolean = data == null

            override suspend fun createCall(): Flow<ApiResponse<TvShowResponse>> = remoteDataSource.getTvShow(id)

            override suspend fun saveCallResult(data: TvShowResponse) {
                localDataSource.getTvShow(id)
            }

        }.asFlow()
    }

    override fun getFavoriteTvShows(): Flow<List<TvShow>> {
        return localDataSource.getFavoriteTvShows().map {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
    }
    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShowEntity, state) }
    }
}