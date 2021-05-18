package com.unhas.simplemovieapp.api

import com.unhas.simplemovieapp.data.response.MovieResponse
import com.unhas.simplemovieapp.data.response.TVSeriesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/top_rated")
    fun getMovie(
        @Query("api_key") apiKey: String
    ): Call<MovieResponse>

    @GET("tv/top_rated")
    fun getTVSeries(
        @Query("api_key") apiKey: String
    ): Call<TVSeriesResponse>
}