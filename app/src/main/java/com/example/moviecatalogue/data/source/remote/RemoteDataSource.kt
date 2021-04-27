package com.example.moviecatalogue.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.moviecatalogue.api.ApiConfig
import com.example.moviecatalogue.data.model.Movie
import com.example.moviecatalogue.data.model.TvShow
import com.example.moviecatalogue.data.source.remote.response.MovieResponse
import com.example.moviecatalogue.data.source.remote.response.TvShowResponse
import retrofit2.Call
import retrofit2.Response

class RemoteDataSource {

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
        client.enqueue(object : retrofit2.Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    movies.postValue(response.body()?.results)
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
        client.enqueue(object : retrofit2.Callback<TvShowResponse> {
            override fun onResponse(call: Call<TvShowResponse>, response: Response<TvShowResponse>) {
                if (response.isSuccessful) {
                    tvShows.postValue(response.body()?.results)
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
}

//        fun getInstance(helper: JsonHelper): RemoteDataSource =
//            instance ?: synchronized(this) {
//                instance ?: RemoteDataSource(helper).apply { instance = this }
//            }

//        // dicoding with api
//        _isLoading.value = true
//        val client = ApiConfig.getApiService().getRestaurant(RESTAURANT_ID)
//        client.enqueue(object : Callback<RestaurantResponse> {
//            override fun onResponse(
//                call: Call<RestaurantResponse>,
//                response: Response<RestaurantResponse>
//            ) {
//                _isLoading.value = false
//                if (response.isSuccessful) {
//                    _restaurant.value = response.body()?.restaurant
//                    _listReview.value = response.body()?.restaurant?.customerReviews
//                } else {
//                    Log.e(TAG, "onFailure: ${response.message()}")
//                }
//            }
//            override fun onFailure(call: Call<RestaurantResponse>, t: Throwable) {
//                _isLoading.value = false
//                Log.e(TAG, "onFailure: ${t.message.toString()}")
//            }
//        })

        // github orang
//        val movies: MutableLiveData<List<MovieResponse>> = MutableLiveData()
//        apiClient.movies(page).enqueue(
//            object : Callback<MovieResponse>{
//                override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
//                    Log.d(TAG, t.localizedMessage)
//                }
//
//                override fun onResponse(
//                    call: Call<MovieResponse>,
//                    response: Response<MovieResponse>
//                ) {
//                    response.body()?.let { movies.postValue(it.results) }
//                }
//
//            }
//        )
//        return movies


//    fun getAllCourses(): List<CourseResponse> = jsonHelper.loadCourses()
//    fun getModules(courseId: String): List<ModuleResponse> = jsonHelper.loadModule(courseId)
//    fun getContent(moduleId: String): ContentResponse = jsonHelper.loadContent(moduleId)



