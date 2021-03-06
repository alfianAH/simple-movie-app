package com.unhas.simplemovieapp.di

import com.unhas.simplemovieapp.data.source.AppRepository
import com.unhas.simplemovieapp.data.source.remote.RemoteDataSource
import com.unhas.simplemovieapp.utils.APILoaderHelper

object Injection {
    fun provideRepository(): AppRepository {
        val remoteDataSource = RemoteDataSource.getInstance(APILoaderHelper())

        return AppRepository.getInstance(remoteDataSource)
    }
}