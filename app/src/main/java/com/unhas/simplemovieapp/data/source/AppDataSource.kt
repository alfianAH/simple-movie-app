package com.unhas.simplemovieapp.data.source

import androidx.lifecycle.LiveData
import com.unhas.simplemovieapp.data.source.remote.response.MovieResultsItem
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResultsItem

interface AppDataSource {
    fun getMovies(apiKey: String)     : LiveData<List<MovieResultsItem>>
    fun getTVSeries(apiKey: String)   : LiveData<List<TVSeriesResultsItem>>
}