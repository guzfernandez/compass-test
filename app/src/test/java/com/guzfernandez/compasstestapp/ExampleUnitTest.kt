package com.guzfernandez.compasstestapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.guzfernandez.compasstestapp.domain.GetWebContentUseCase
import com.guzfernandez.compasstestapp.viewmodel.CompassViewModel
import io.mockk.coEvery
import kotlinx.coroutines.test.runTest

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var getWebContentUseCase: GetWebContentUseCase

    private lateinit var compassViewModel: CompassViewModel

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        compassViewModel = CompassViewModel(getWebContentUseCase)
    }

    @Test
    fun `Every10thCharacterRequest updates every10thCharacterList correctly`() = runTest {
        val content = "abcdefghijabcdefghijabcdefghij"
        `when`(getWebContentUseCase.invoke()).thenReturn(content)

        val observer = mock(Observer::class.java) as Observer<List<Char>>
        compassViewModel.every10thCharacterList.observeForever(observer)

        compassViewModel.getWebContentFromCompass()

        val expected = listOf('j', 'j', 'j')
        verify(observer).onChanged(expected)
        assertEquals(expected, compassViewModel.every10thCharacterList.value)
    }

    @Test
    fun `WordCounterRequest updates wordCountList correctly`() = runTest {
        val content = "Hello world! Hello everyone."
        `when`(getWebContentUseCase.invoke()).thenReturn(content)

        val observer = mock(Observer::class.java) as Observer<Map<String, Int>>
        compassViewModel.wordCountList.observeForever(observer)

        compassViewModel.getWebContentFromCompass()

        val expected = mapOf("hello" to 2, "world!" to 1, "everyone." to 1)
        verify(observer).onChanged(expected)
        assertEquals(expected, compassViewModel.wordCountList.value)
    }
}