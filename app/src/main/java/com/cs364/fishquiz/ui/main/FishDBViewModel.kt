package com.cs364.fishquiz.ui.main

import android.content.Context
import androidx.lifecycle.ViewModel
import com.cs364.fishquiz.ui.data.Fish
import com.cs364.fishquiz.ui.data.FishDatabase
import com.cs364.fishquiz.ui.data.FishRepository
import com.cs364.fishquiz.ui.data.Habitat
import kotlinx.coroutines.flow.*

/**
 * Connects the database to the UI of the app. Functions are used to pull objects or lists
 * as Flows.
 *
 * @param context the context used to create the database
 */
class FishDBViewModel(
    context: Context
): ViewModel() {
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

    fun getAllFishInHabitat(hab: String): Flow<List<Fish>> {
        return fishRepository.getAllFishInHabitat(hab)
    }

    fun getHabitatFromId(id: Int): Flow<Habitat> {
        return fishRepository.getHabitatFromId(id)
    }
}