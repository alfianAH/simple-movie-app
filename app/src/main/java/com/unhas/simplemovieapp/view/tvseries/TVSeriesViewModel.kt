package com.unhas.simplemovieapp.view.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.unhas.simplemovieapp.BuildConfig
import com.unhas.simplemovieapp.data.source.AppRepository
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResultsItem

class TVSeriesViewModel(private val appRepository: AppRepository): ViewModel() {

    companion object{
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getTVSeries(): LiveData<List<TVSeriesResultsItem>> =
        appRepository.getTVSeries(API_KEY)
}