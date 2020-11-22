package com.usman.hanseaticsaufgabe.repository

import com.usman.hanseaticsaufgabe.network.RetrofitInstance

class AppRepository {

    suspend fun getMovies() = RetrofitInstance.api.getPictures("100")
}