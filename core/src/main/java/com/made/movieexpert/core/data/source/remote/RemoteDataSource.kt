package com.made.movieexpert.core.data.source.remote

import com.made.movieexpert.core.data.source.remote.network.ApiResponse
import com.made.movieexpert.core.data.source.remote.network.ApiService
import com.made.movieexpert.core.data.source.remote.response.MovieResponse
import com.made.movieexpert.core.data.source.remote.response.TvShowResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(private val apiService: ApiService) {

    suspend fun getMovies(page: Int): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val response = apiService.getMovies(page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString() ))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShows(page: Int): Flow<ApiResponse<List<TvShowResponse>>> {
        return flow {
            try {
                val response = apiService.getTvShows(page)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString() ))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getMovie(id: Int): Flow<ApiResponse<MovieResponse>> {
        return flow {
            try {
                val response = apiService.getMovie(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString() ))
            }
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTvShow(id: Int): Flow<ApiResponse<TvShowResponse>> {
        return flow {
            try {
                val response = apiService.getTvShow(id)
                emit(ApiResponse.Success(response))
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString() ))
            }
        }.flowOn(Dispatchers.IO)
    }

}
