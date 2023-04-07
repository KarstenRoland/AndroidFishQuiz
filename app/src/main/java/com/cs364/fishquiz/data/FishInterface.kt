package com.cs364.fishquiz.data

import kotlinx.coroutines.flow.Flow

interface FishInterface {
    fun getAllFish(): Flow<List<Fish>>

    fun getAllFacts(): Flow<List<FishFact>>

    fun getAllHabs(): Flow<List<Habitat>>
}