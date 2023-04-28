package com.cs364.fishquiz.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.data.FishRepository
import kotlinx.coroutines.flow.*

class FishDBViewModel(fishRepository: FishRepository): ViewModel() {
    val fishUiState: StateFlow<FishUIState> = fishRepository.getAllFish().map { FishUIState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = FishUIState()
        )
    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
    /*
    fun getAllFish(): Flow<List<Fish>> = fishRepository.getAllFish()
    fun getFishFromId(id: Int): Flow<Fish> = fishRepository.getFishFromId(id)
     */
}

data class FishUIState(val fishList: List<Fish> = listOf())