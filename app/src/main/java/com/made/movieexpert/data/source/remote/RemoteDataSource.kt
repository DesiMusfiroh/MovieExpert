package com.made.movieexpert.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.made.movieexpert.api.ApiConfig
import com.made.movieexpert.api.ApiResponse
import com.made.movieexpert.data.model.MovieRes
import com.made.movieexpert.data.model.SeasonRes
import com.made.movieexpert.data.model.TvShowRes
import com.made.movieexpert.data.source.remote.response.MovieResponse
import com.made.movieexpert.data.source.remote.response.TvShowResponse
import com.made.movieexpert.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource {

    companion object {
        fun getInstance(): RemoteDataSource {
            return RemoteDataSource()
        }
        private const val TAG = "RemoteDataSource"
    }

    fun getMovies(page: Int): LiveData<ApiResponse<List<MovieRes>>> {
        val resultMovies = MutableLiveData<ApiResponse<List<MovieRes>>>()
        val client = ApiConfig.getApiService().getMovies(page)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    resultMovies.value = ApiResponse.success(response.body()!!.results)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return resultMovies
    }

    fun getTvShows(page: Int): LiveData<ApiResponse<List<TvShowRes>>> {
        val resultTvShows = MutableLiveData<ApiResponse<List<TvShowRes>>>()
        val client = ApiConfig.getApiService().getTvShows(page)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    resultTvShows.value = ApiResponse.success(response.body()!!.results)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return resultTvShows
    }

    fun getMovie(id: Int): LiveData<ApiResponse<MovieRes>> {
        val movie = MutableLiveData<ApiResponse<MovieRes>>()
        val client = ApiConfig.getApiService().getMovie(id)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<MovieRes> {
            override fun onResponse(call: Call<MovieRes>, response: Response<MovieRes>) {
                if (response.isSuccessful) {
                    movie.value = ApiResponse.success(response.body()!!)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieRes>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return movie
    }

    fun getTvShow(id: Int) : LiveData<ApiResponse<TvShowRes>>{
        val tvShow = MutableLiveData<ApiResponse<TvShowRes>>()
        val client = ApiConfig.getApiService().getTvShow(id)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<TvShowRes> {
            override fun onResponse(call: Call<TvShowRes>, response: Response<TvShowRes>) {
                if (response.isSuccessful) {
                    tvShow.value = ApiResponse.success(response.body()!!)
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowRes>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return tvShow
    }


    fun getSeasonsByTvShow(id: Int) : LiveData<List<SeasonRes>>{
        val seasonsByTvShow: MutableLiveData<List<SeasonRes>> = MutableLiveData()
        val client = ApiConfig.getApiService().getTvShow(id)
        EspressoIdlingResource.increment()

        client.enqueue(object : Callback<TvShowRes> {
            override fun onResponse(call: Call<TvShowRes>, response: Response<TvShowRes>) {
                if (response.isSuccessful) {
                    seasonsByTvShow.postValue(response.body()?.season)
                    Log.d("season", "remote data source inside get ${seasonsByTvShow.value}")
                    EspressoIdlingResource.decrement()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TvShowRes>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message.toString()}")
            }
        })
        return seasonsByTvShow
    }
}
