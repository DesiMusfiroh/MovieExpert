package com.example.moviecatalogue.api

import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.TvShow
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

    @GET("movie/{id}?api_key=$API_KEY")
    fun getMovie(@Path("id") id: Int): Call<Movie>

    @GET("tv/{id}?api_key=$API_KEY")
    fun getTvShow(@Path("id") id: Int): Call<TvShow>

}