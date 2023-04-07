package com.cs364.fishquiz.data

import kotlinx.coroutines.flow.Flow

class FishRepository(private val fishDao: FishDao) : FishInterface {
    override fun getAllFacts(): Flow<List<FishFact>> = fishDao.getAllFacts()

    override fun getAllFish(): Flow<List<Fish>> = fishDao.getAllFish()

    override fun getAllHabs(): Flow<List<Habitat>> = fishDao.getAllHabs()
}