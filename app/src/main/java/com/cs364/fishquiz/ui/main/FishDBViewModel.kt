package com.cs364.fishquiz.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.data.FishDatabase
import com.cs364.fishquiz.ui.data.FishRepository
import kotlinx.coroutines.flow.*

class FishDBViewModel(context: Context): ViewModel() {
    private val fishRepository: FishRepository

    init {
        val daos = FishDatabase.getInstance(context).fishDao()
        fishRepository = FishRepository(daos)
    }

    fun getAllFish(): Flow<List<Fish>> {
       return fishRepository.getAllFish()
    }
    fun getFishFromId(id: Int): Flow<Fish> {
        return fishRepository.getFishFromId(id)
    }
}