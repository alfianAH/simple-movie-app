package com.unhas.simplemovieapp.view.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.unhas.simplemovieapp.BuildConfig
import com.unhas.simplemovieapp.data.source.AppRepository
import com.unhas.simplemovieapp.data.source.remote.response.MovieResultsItem

class MovieViewModel(private val movieAppRepository: AppRepository): ViewModel() {

    companion object{
        private const val API_KEY = BuildConfig.TMBDApiKey
    }

    fun getMovies(): LiveData<List<MovieResultsItem>> =
        movieAppRepository.getMovies(API_KEY)
}