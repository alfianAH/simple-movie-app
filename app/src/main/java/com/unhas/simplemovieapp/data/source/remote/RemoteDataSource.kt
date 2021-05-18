package com.unhas.simplemovieapp.data.source.remote

import com.unhas.simplemovieapp.data.source.remote.response.MovieResponse
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResponse
import com.unhas.simplemovieapp.utils.APILoaderHelper
import retrofit2.Call

class RemoteDataSource private constructor(private val apiLoaderHelper: APILoaderHelper) {

    companion object {
        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(apiLoaderHelper: APILoaderHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(apiLoaderHelper)
            }
    }

    fun getMovies(apiKey: String, callback: LoadMoviesCallback){
        callback.onAllMoviesReceived(apiLoaderHelper.findMovies(apiKey))
    }

    fun getTVSeries(apiKey: String, callback: LoadTVSeriesCallback){
        callback.onAllTVSeriesReceived(apiLoaderHelper.findTVSeries(apiKey))
    }

    /**
     * Interfaces to call client for each responses
     */

    interface LoadMoviesCallback{
        fun onAllMoviesReceived(client : Call<MovieResponse>)
    }

    interface LoadTVSeriesCallback{
        fun onAllTVSeriesReceived(client: Call<TVSeriesResponse>)
    }
}