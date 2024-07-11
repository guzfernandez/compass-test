package com.guzfernandez.compasstestapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.guzfernandez.compasstestapp.domain.GetWebContentUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CompassViewModel @Inject constructor(
    private val getWebContentUseCase: GetWebContentUseCase,
) : ViewModel() {

    var every10thCharacterList = MutableLiveData<List<Char>>()
    var wordCountList = MutableLiveData<Map<String, Int>>()

    fun getWebContentFromCompass() {
        runRequestsInParallel()
    }

    private fun runRequestsInParallel()  {
        viewModelScope.launch {
             awaitAll(
                async { Every10thCharacterRequest() },
                async { WordCounterRequest() }
            )
        }
    }

    private suspend fun Every10thCharacterRequest() {
        val result = getWebContentUseCase().toString()

        val list = mutableListOf<Char>()
        for (i in 9 until result.length step 10) {
            list.add(result[i])
        }

        every10thCharacterList.postValue(list)
    }

    private suspend fun WordCounterRequest() {
        val result = getWebContentUseCase().toString()
        val words = result.split("\\s+".toRegex())
            .map { it.trim().lowercase() }
            .filter { it.isNotEmpty() }
        val finalResult = words.groupingBy { it }.eachCount()

        wordCountList.postValue(finalResult)
    }

}