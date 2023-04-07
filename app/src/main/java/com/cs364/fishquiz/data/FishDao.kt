package com.cs364.fishquiz.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FishDao {
    @Query("SELECT * FROM fish")
    fun getAllFish(): Flow<List<Fish>>

    @Query("SELECT * FROM fish_fact")
    fun getAllFacts(): Flow<List<FishFact>>

    @Query("SELECT * FROM habitat")
    fun getAllHabs(): Flow<List<Habitat>>



}