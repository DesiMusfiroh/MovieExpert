package com.example.moviecatalogue.api

import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import com.example.moviecatalogue.utils.Constants.API_KEY
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

//    @GET("search/movie")
//    @Headers("Authorization: token a5856623a69510eec77bfc3bd7116652")
//    fun getMovies (
//        @Query("q") query: Int
//    ) : Call<MovieResponse>
//
//    @GET("detail/{id}")
//    fun getMovie(
//        @Path("id") id: String
//    ): Call<MovieResponse>
}