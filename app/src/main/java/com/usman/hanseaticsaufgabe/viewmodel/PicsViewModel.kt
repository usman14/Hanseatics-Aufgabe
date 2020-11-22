package com.usman.hanseaticsaufgabe.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.usman.hanseaticsaufgabe.R
import com.usman.hanseaticsaufgabe.app.MyApplication
import com.usman.hanseaticsaufgabe.model.MovieResponse
import com.usman.hanseaticsaufgabe.repository.AppRepository
import com.usman.hanseaticsaufgabe.util.Resource
import com.usman.hanseaticsaufgabe.util.Utils.hasInternetConnection
import kotlinx.coroutines.launch
import retrofit2.Response
import java.io.IOException

class PicsViewModel(
    app: Application,
    private val appRepository: AppRepository
) : AndroidViewModel(app) {

    val movieData: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()

    init {
        getPictures()
    }

    fun getPictures() = viewModelScope.launch {
        fetchPics()
    }

    private suspend fun fetchPics() {
        movieData.postValue(Resource.Loading())
        try {
            if (hasInternetConnection(getApplication<MyApplication>())) {
                val response = appRepository.getMovies()
                movieData.postValue(handlePicsResponse(response))
            } else {
                movieData.postValue(Resource.Error(getApplication<MyApplication>().getString(R.string.no_internet_connection)))
            }
        } catch (t: Throwable) {
            when (t) {
                is IOException -> movieData.postValue(
                    Resource.Error(
                            getApplication<MyApplication>().getString(
                            R.string.network_failure
                        )
                    )
                )
                else -> movieData.postValue(
                    Resource.Error(
                        getApplication<MyApplication>().getString(
                            R.string.conversion_error
                        )
                    )
                )
            }
        }
    }

    private fun handlePicsResponse(response: Response<MovieResponse>): Resource<MovieResponse> {
        if (response.isSuccessful) {
            response.body()?.let { resultResponse ->
                return Resource.Success(resultResponse)
            }
        }
        return Resource.Error(response.message())
    }
}