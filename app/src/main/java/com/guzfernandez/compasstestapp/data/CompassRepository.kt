package com.guzfernandez.compasstestapp.data

import com.guzfernandez.compasstestapp.data.database.dao.WebContentDao
import com.guzfernandez.compasstestapp.data.database.entities.WebContentEntity
import com.guzfernandez.compasstestapp.data.network.WebContentService
import javax.inject.Inject

class CompassRepository @Inject constructor(
    private val api: WebContentService,
    private val quoteDao: WebContentDao
) {

    suspend fun getWebContentFromApi(): Any? {
        return api.getWebContent()
    }

    suspend fun getWebContentFromDatabase() : String {
        val response: List<WebContentEntity> = quoteDao.getAllQuotes()
        return response.firstOrNull()?.response.toString()
    }

    suspend fun insertWebContent(webContent: List<WebContentEntity>) {
        quoteDao.insertAll(webContent)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}