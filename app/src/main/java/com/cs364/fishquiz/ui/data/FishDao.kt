package com.cs364.fishquiz.ui.data

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Insert
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Queries used to get data from the fish database.
 */
@Dao
interface FishDao {
    @Query("SELECT * FROM fish")
    fun getAllFish(): Flow<List<Fish>>

    @Query("SELECT * FROM fish_fact")
    fun getAllFacts(): Flow<List<FishFact>>

    @Query("SELECT * FROM habitat")
    fun getAllHabs(): Flow<List<Habitat>>

    @Query("SELECT * FROM fish WHERE common_name = :name")
    fun getFishFromName(name: String): Flow<Fish>

    @Query("SELECT * FROM fish WHERE fish_id = :id")
    fun getFishFromId(id: Int): Flow<Fish>

    @Query("SELECT F.* FROM fish F " +
            "JOIN Habitat H ON F.hab_id = H.habitat_id " +
            "WHERE H.water_type = :habitat")
    fun getAllFishInHabitat(habitat: String): Flow<List<Fish>>
}