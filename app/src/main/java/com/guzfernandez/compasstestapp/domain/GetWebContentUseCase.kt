package com.guzfernandez.compasstestapp.domain

import com.guzfernandez.compasstestapp.data.CompassRepository
import com.guzfernandez.compasstestapp.data.database.entities.WebContentEntity
import javax.inject.Inject

class GetWebContentUseCase @Inject constructor(
    private val repository: CompassRepository
) {

    suspend operator fun invoke(): Any {
        val response = repository.getWebContentFromApi()

        return if (response != null) {
            repository.clearQuotes()
            val test = listOf(WebContentEntity(0, response.toString()))
            repository.insertWebContent(test)
            response
        } else {
            repository.getWebContentFromDatabase()
        }
    }
}