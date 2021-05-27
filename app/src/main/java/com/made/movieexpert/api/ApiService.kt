package com.made.movieexpert.api

import com.made.movieexpert.data.model.MovieRes
import com.made.movieexpert.data.model.TvShowRes
import com.made.movieexpert.data.source.remote.response.MovieResponse
import com.made.movieexpert.data.source.remote.response.TvShowResponse
import com.made.movieexpert.utils.Constants.API_KEY
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("movie/popular?api_key=$API_KEY")
    fun getMovies(
        @Query("page") page: Int
    ) : Call<MovieResponse>

    @GET("tv/popular?api_key=$API_KEY")
    fun getTvShows(
        @Query("page") page: Int
    ) : Call<TvShowResponse>

    @GET("movie/{id}?api_key=$API_KEY")
    fun getMovie(
        @Path("id") id: Int
    ) : Call<MovieRes>

    @GET("tv/{id}?api_key=$API_KEY")
    fun getTvShow(
        @Path("id") id: Int
    ) : Call<TvShowRes>

}