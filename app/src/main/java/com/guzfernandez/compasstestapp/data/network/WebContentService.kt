package com.guzfernandez.compasstestapp.data.network

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WebContentService @Inject constructor(private val api: WebContentApiClient) {

    suspend fun getWebContent(): Any? {
        return withContext(Dispatchers.IO) {
            try {
                val response = api.getWebContent()
                response.body()?.string()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
        }
    }

}