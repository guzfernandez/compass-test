package com.guzfernandez.compasstestapp.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface WebContentApiClient {

    @GET("/about")
    suspend fun getWebContent(): Response<ResponseBody>
}