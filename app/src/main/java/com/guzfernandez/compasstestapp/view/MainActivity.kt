package com.guzfernandez.compasstestapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.guzfernandez.compasstestapp.databinding.ActivityMainBinding
import com.guzfernandez.compasstestapp.viewmodel.CompassViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val compassViewModel: CompassViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        compassViewModel.every10thCharacterList.observe(this) {
            binding.textviewOne.text = it.toString()
        }

        compassViewModel.wordCountList.observe(this) {
            val sortedDesc = it.entries.sortedByDescending { it.value }
            val formattedString = sortedDesc.joinToString("\n") { "${it.key}: ${it.value}" }
            binding.textviewTwo.text = formattedString
        }

        binding.btnRequests.setOnClickListener {
            runRequests()
        }

    }

    private fun runRequests() {
        compassViewModel.getWebContentFromCompass()
    }

}