package com.cs364.fishquiz.data

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

/**
 * Fish entity for perpetual storage of the different fish that will be displayed
 * in the app.
 *
 * @param fishID primary key for the Fish entity. In the form of a INT
 * @param commonName the unlatinized name for this fish. string
 * @param habitatID the foreign key for relating a fish entry to its habitat
 */
@Entity(tableName = "fish", foreignKeys = [ForeignKey(
    entity = Habitat::class,
    parentColumns = arrayOf("habitatID"),
    childColumns = arrayOf("habID"),
    onDelete = ForeignKey.CASCADE
)])
data class Fish(
    @PrimaryKey
    @ColumnInfo(name = "fish_id")
    val fishID: Int = 0,
    @ColumnInfo(name = "common_name")
    val commonName: String,
    @ColumnInfo(name = "habitat_id")
    val habitatID: Int,
    @ColumnInfo(name = "genus")
    val genus: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "water_depth_met")
    val waterDepthMeters: Int,
    @ColumnInfo(name = "avg_len_met")
    val avgLengthMeters: Double,
    @ColumnInfo(name = "avg_weight_kg")
    val avgWeightKg: Double,
    @ColumnInfo(name = "desc")
    val description: String
)

/**
 * Habitat entity for perpetual storage of the different habitats for fish
 *
 * @param habID primary key for the Habitat entity
 * @param waterType the type of water that this habitat contains. Salt, Fresh, Brackish
 * @param location various locations where this habitat can be found on earth
 */
@Entity(tableName = "habitat")
data class Habitat(
    @PrimaryKey
    @ColumnInfo(name = "hab_id")
    val habID: Int = 0,
    @ColumnInfo(name = "water_type")
    val waterType: String,
    @ColumnInfo(name = "location")
    val location: String
)

/**
 * FishFact entity for perpetual storage of facts about fish.
 *
 * @param factID primary key in the form of a INT
 * @param fact the actual fact being stored
 * @param idFish foreign key showing relation to the fish entity
 */
@Entity(tableName = "fish_fact", foreignKeys = [ForeignKey(
    entity = Fish::class,
    parentColumns = arrayOf("idFish"),
    childColumns = arrayOf("fishID"),
    onDelete = ForeignKey.CASCADE
)])
data class FishFact(
    @PrimaryKey
    @ColumnInfo(name = "fact_id")
    val factID: Int = 0,
    @ColumnInfo(name = "fact")
    val fact: String,
    @ColumnInfo(name = "id_fish")
    val idFish: Int
)