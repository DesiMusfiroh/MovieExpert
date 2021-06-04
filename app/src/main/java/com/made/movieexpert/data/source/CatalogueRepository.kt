package com.made.movieexpert.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.made.movieexpert.api.ApiResponse
import com.made.movieexpert.data.source.remote.model.SeasonRes
import com.made.movieexpert.data.source.local.LocalDataSource
import com.made.movieexpert.data.source.local.entity.MovieEntity
import com.made.movieexpert.data.source.local.entity.TvShowEntity
import com.made.movieexpert.data.source.remote.RemoteDataSource
import com.made.movieexpert.data.source.remote.model.MovieRes
import com.made.movieexpert.data.source.remote.model.TvShowRes
import com.made.movieexpert.domain.model.Movie
import com.made.movieexpert.domain.model.TvShow
import com.made.movieexpert.domain.repository.ICatalogueRepository
import com.made.movieexpert.utils.AppExecutors
import com.made.movieexpert.utils.DataMapper
import com.made.movieexpert.vo.Resource

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

    override fun getMovies(page: Int): LiveData<Resource<List<Movie>>> {
        return object : NetworkBoundResource<List<Movie>, List<MovieRes>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<Movie>> {
                return Transformations.map(localDataSource.getMovies()) {
                    DataMapper.mapMovieEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<MovieRes>>> = remoteDataSource.getMovies(1)

            override fun saveCallResult(data: List<MovieRes>) {
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

    override fun getMovie(id: Int): LiveData<Resource<Movie>> {
        return object : NetworkBoundResource<Movie, MovieRes>(appExecutors) {
            override fun loadFromDB(): LiveData<Movie> {
                return localDataSource.getMovie(id) as LiveData<Movie>
            }

            override fun shouldFetch(data: Movie?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<MovieRes>> = remoteDataSource.getMovie(id)

            override fun saveCallResult(data: MovieRes) {
                localDataSource.getMovie(id)
            }

        }.asLiveData()
    }

    override fun getFavoriteMovies(): LiveData<List<Movie>> {
        return Transformations.map(localDataSource.getFavoriteMovies()) {
            DataMapper.mapMovieEntitiesToDomain(it)
        }
    }

    override fun setMovieFavorite(movie: Movie, state: Boolean) {
        val movieEntity = DataMapper.mapMovieDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movieEntity, state) }
    }

    override fun getTvShows(page: Int): LiveData<Resource<List<TvShow>>> {
        return object : NetworkBoundResource<List<TvShow>, List<TvShowRes>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<TvShow>> {
                return Transformations.map(localDataSource.getTvShows()) {
                    DataMapper.mapTvShowEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<TvShow>?): Boolean = data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShowRes>>> = remoteDataSource.getTvShows(1)

            override fun saveCallResult(data: List<TvShowRes>) {
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

    override fun getTvShow(id: Int): LiveData<Resource<TvShow>> {
        return object : NetworkBoundResource<TvShow, TvShowRes>(appExecutors) {
            override fun loadFromDB(): LiveData<TvShow> = localDataSource.getTvShow(id) as LiveData<TvShow>

            override fun shouldFetch(data: TvShow?): Boolean = data == null

            override fun createCall(): LiveData<ApiResponse<TvShowRes>> = remoteDataSource.getTvShow(id)

            override fun saveCallResult(data: TvShowRes) {
                localDataSource.getTvShow(id)
            }

        }.asLiveData()
    }

    override fun getFavoriteTvShows(): LiveData<List<TvShow>> {
        return Transformations.map(localDataSource.getFavoriteTvShows()) {
            DataMapper.mapTvShowEntitiesToDomain(it)
        }
    }

    override fun setTvShowFavorite(tvShow: TvShow, state: Boolean) {
        val tvShowEntity = DataMapper.mapTvShowDomainToEntity(tvShow)
        appExecutors.diskIO().execute { localDataSource.setTvShowFavorite(tvShowEntity, state) }
    }

    override fun getSeasonsByTvShow(tvShowId: Int): LiveData<List<SeasonRes>> = remoteDataSource.getSeasonsByTvShow(tvShowId)

}