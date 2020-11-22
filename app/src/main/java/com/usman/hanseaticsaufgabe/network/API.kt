package com.usman.hanseaticsaufgabe.network

import com.usman.hanseaticsaufgabe.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface API {

    @GET("api/characters")
    suspend fun getPictures(@Query ("limit") limit:String): Response<MovieResponse>
}