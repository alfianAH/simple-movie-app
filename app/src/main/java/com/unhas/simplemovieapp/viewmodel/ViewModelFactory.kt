package com.unhas.simplemovieapp.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.unhas.simplemovieapp.data.source.AppRepository
import com.unhas.simplemovieapp.di.Injection
import com.unhas.simplemovieapp.view.movie.MovieViewModel
import com.unhas.simplemovieapp.view.tvseries.TVSeriesViewModel

class ViewModelFactory private constructor(private val appRepository: AppRepository):
    ViewModelProvider.NewInstanceFactory(){

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this){
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when{

            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                MovieViewModel(appRepository) as T
            }

            modelClass.isAssignableFrom(TVSeriesViewModel::class.java) -> {
                TVSeriesViewModel(appRepository) as T
            }

            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}