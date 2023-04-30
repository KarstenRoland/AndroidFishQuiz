package com.cs364.fishquiz.ui.data

import kotlinx.coroutines.flow.Flow

interface FishInterface {
    fun getAllFish(): Flow<List<Fish>>

    fun getAllFacts(): Flow<List<FishFact>>

    fun getAllHabs(): Flow<List<Habitat>>

    fun getFishFromId(id: Int): Flow<Fish>

    fun getFishFromName(name: String): Flow<Fish>

    fun getAllFishInHabitat(habitat: Int): Flow<List<Fish>>

}