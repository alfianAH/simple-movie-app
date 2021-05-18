package com.unhas.simplemovieapp.data.source.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unhas.simplemovieapp.BuildConfig
import com.unhas.simplemovieapp.data.source.remote.response.MovieResponse
import com.unhas.simplemovieapp.data.source.remote.response.MovieResultsItem
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResponse
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResultsItem
import com.unhas.simplemovieapp.utils.APILoaderHelper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource private constructor(private val apiLoaderHelper: APILoaderHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiLoaderHelper: APILoaderHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiLoaderHelper)
            }

        private const val TAG = "RemoteDataSource"
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getMovies(): LiveData<ApiResponse<List<MovieResultsItem>>> {

        val resultMovie = MutableLiveData<ApiResponse<List<MovieResultsItem>>>()

        apiLoaderHelper.findMovies(API_KEY).enqueue(object : Callback<MovieResponse> {
            override fun onResponse(call: Call<MovieResponse>, response: Response<MovieResponse>) {
                if (response.isSuccessful) {
                    resultMovie.value =
                        ApiResponse.success(response.body()?.results as List<MovieResultsItem>)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultMovie
    }

    fun getTVSeries(): LiveData<ApiResponse<List<TVSeriesResultsItem>>> {
        val resultTVSeries = MutableLiveData<ApiResponse<List<TVSeriesResultsItem>>>()

        apiLoaderHelper.findTVSeries(API_KEY).enqueue(object : Callback<TVSeriesResponse> {
            override fun onResponse(
                call: Call<TVSeriesResponse>,
                response: Response<TVSeriesResponse>
            ) {
                if (response.isSuccessful) {
                    resultTVSeries.value =
                        ApiResponse.success(response.body()?.results as List<TVSeriesResultsItem>)
                } else {
                    Log.e(TAG, "onResponse: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })

        return resultTVSeries
    }
}