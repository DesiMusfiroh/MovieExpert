package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.Season
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import com.example.moviecatalogue.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {
    val seasonsByTvShow: MutableLiveData<List<Season>> = MutableLiveData()
    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null
        private const val TAG = "RemoteDataSource"

        fun getInstance(): RemoteDataSource {
            return RemoteDataSource()
        }
    }

    fun getMovies(page: Int): LiveData<List<Movie>> {

        val movies: MutableLiveData<List<Movie>> = MutableLiveData()
        val client = ApiConfig.getApiService().getMovies(page)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.postValue(response.body()?.results)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movies
    }

    fun getTvShows(page: Int): LiveData<List<TvShow>> {

        val tvShows: MutableLiveData<List<TvShow>> = MutableLiveData()
        val client = ApiConfig.getApiService().getTvShows(page)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    tvShows.postValue(response.body()?.results)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return tvShows
    }

    fun getMovie(id: Int): LiveData<Movie> {

        val movie: MutableLiveData<Movie> = MutableLiveData()
        val client = ApiConfig.getApiService().getMovie(id)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<Movie> {
            override fun onResponse(call: Call<Movie>, response: Response<Movie>) {
                if (response.isSuccessful) {
                    movie.postValue(response.body())
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<Movie>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movie
    }

    fun getTvShow(id: Int) : LiveData<TvShow>{

        val tvShow: MutableLiveData<TvShow> = MutableLiveData()
        val client = ApiConfig.getApiService().getTvShow(id)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<TvShow> {
            override fun onResponse(call: Call<TvShow>, response: Response<TvShow>) {
                if (response.isSuccessful) {
                    tvShow.postValue(response.body())
                    seasonsByTvShow.postValue(response.body()?.season)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShow>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return tvShow
    }

}
