package com.unhas.simplemovieapp.utils

import com.unhas.simplemovieapp.api.ApiConfig
import com.unhas.simplemovieapp.data.source.remote.response.MovieResponse
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResponse
import retrofit2.Call

class APILoaderHelper {

    fun findMovies(apiKey: String): Call<MovieResponse> {
        return ApiConfig.getApiService().getMovie(apiKey)
    }

    fun findTVSeries(apiKey: String): Call<TVSeriesResponse> {
        return ApiConfig.getApiService().getTVSeries(apiKey)
    }
}