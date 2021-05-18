package com.unhas.simplemovieapp.data.source

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.unhas.simplemovieapp.data.source.remote.RemoteDataSource
import com.unhas.simplemovieapp.data.source.remote.response.MovieResponse
import com.unhas.simplemovieapp.data.source.remote.response.MovieResultsItem
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResponse
import com.unhas.simplemovieapp.data.source.remote.response.TVSeriesResultsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AppRepository private constructor(
    private val remoteDataSource: RemoteDataSource
):
    AppDataSource {

    companion object {
        @Volatile
        private var instance: AppRepository? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource
        ): AppRepository =
            instance ?: synchronized(this) {
                instance ?: AppRepository(remoteDataSource)
            }

        private const val TAG = "AppRepository"
    }

    override fun getMovies(apiKey: String): LiveData<List<MovieResultsItem>> {
        val movieResults = MutableLiveData<List<MovieResultsItem>>()

        // Get movie
        remoteDataSource.getMovies(apiKey, object: RemoteDataSource.LoadMoviesCallback {

            override fun onAllMoviesReceived(client: Call<MovieResponse>) {
                // Get movie from API
                client.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(call: Call<MovieResponse>,
                                            response: Response<MovieResponse>
                    ) {
                        // If response is successful, post value
                        if(response.isSuccessful){
                            movieResults.postValue(response.body()?.results)
                        } else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<MovieResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })

        return movieResults
    }

    override fun getTVSeries(apiKey: String): LiveData<List<TVSeriesResultsItem>> {
        val tvSeriesResults = MutableLiveData<List<TVSeriesResultsItem>>()

        // Get TV Series
        remoteDataSource.getTVSeries(apiKey, object: RemoteDataSource.LoadTVSeriesCallback {

            override fun onAllTVSeriesReceived(client: Call<TVSeriesResponse>) {
                // Get TV Series from API
                client.enqueue(object : Callback<TVSeriesResponse> {
                    override fun onResponse(call: Call<TVSeriesResponse>,
                                            response: Response<TVSeriesResponse>
                    ) {
                        // If response is successful, post value
                        if(response.isSuccessful){
                            tvSeriesResults.postValue(response.body()?.results)
                        } else{
                            Log.e(TAG, "onFailure: ${response.message()}")
                        }
                    }

                    override fun onFailure(call: Call<TVSeriesResponse>, t: Throwable) {
                        Log.e(TAG, "onFailure: ${t.message.toString()}")
                    }
                })
            }
        })

        return tvSeriesResults
    }
}