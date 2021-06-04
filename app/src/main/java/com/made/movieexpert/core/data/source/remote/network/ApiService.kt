package com.made.movieexpert.core.data.source.remote.network

import com.made.movieexpert.core.data.source.remote.response.MovieResponse
import com.made.movieexpert.core.data.source.remote.response.TvShowResponse
import com.made.movieexpert.core.data.source.remote.response.ListMovieResponse
import com.made.movieexpert.core.data.source.remote.response.ListTvShowResponse
import com.made.movieexpert.core.utils.Constants.API_KEY
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular?api_key=$API_KEY")
    suspend fun getMovies(
        @Query("page") page: Int
    ) : ListMovieResponse

    @GET("tv/popular?api_key=$API_KEY")
    suspend fun getTvShows(
        @Query("page") page: Int
    ) : ListTvShowResponse

    @GET("movie/{id}?api_key=$API_KEY")
    suspend fun getMovie(
        @Path("id") id: Int
    ) : MovieResponse

    @GET("tv/{id}?api_key=$API_KEY")
    suspend fun getTvShow(
        @Path("id") id: Int
    ) : TvShowResponse

}