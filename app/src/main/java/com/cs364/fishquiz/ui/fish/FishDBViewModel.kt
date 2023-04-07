package com.cs364.fishquiz.ui.fish

import androidx.lifecycle.ViewModel
import com.cs364.fishquiz.data.FishRepository

class FishDBViewModel(fishRepository: FishRepository): ViewModel() {
    val allFish = fishRepository.getAllFish()
    val allFact = fishRepository.getAllFacts()
    val allHabs = fishRepository.getAllHabs()
}