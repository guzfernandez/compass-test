package com.guzfernandez.compasstestapp.data

import com.guzfernandez.compasstestapp.data.database.dao.WebContentDao
import com.guzfernandez.compasstestapp.data.database.entities.WebContentEntity
import com.guzfernandez.compasstestapp.data.network.WebContentService
import javax.inject.Inject

class CompassRepository @Inject constructor(
    private val api: WebContentService,
    private val webContentDao: WebContentDao
) {

    suspend fun getWebContentFromApi(): Any? {
        return api.getWebContent()
    }

    suspend fun getWebContentFromDatabase() : String {
        val response: List<WebContentEntity> = webContentDao.getAllWebContent()
        return response.firstOrNull()?.response.toString()
    }

    suspend fun insertWebContent(webContent: List<WebContentEntity>) {
        webContentDao.insertAll(webContent)
    }

    suspend fun clearQuotes(){
        webContentDao.deleteAllWebContent()
    }
}