package com.cs364.fishquiz.ui.data

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
    parentColumns = arrayOf("habitat_id"),
    childColumns = arrayOf("hab_id"),
    onDelete = ForeignKey.NO_ACTION
)])
data class Fish(
    @PrimaryKey
    @ColumnInfo(name = "fish_id")
    val fish_id: Int = 0,
    @ColumnInfo(name = "common_name")
    val common_name: String,
    @ColumnInfo(name = "species")
    val species: String,
    @ColumnInfo(name = "genus")
    val genus: String,
    @ColumnInfo(name = "avg_weight_kg")
    val avg_weight_kg: Double,
    @ColumnInfo(name = "avg_len_met")
    val avg_len_met: Double,
    @ColumnInfo(name = "water_depth_met")
    val water_depth_met: Int,
    @ColumnInfo(name = "desc")
    val desc: String,
    @ColumnInfo(name = "hab_id")
    val hab_id: Int
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
    @ColumnInfo(name = "habitat_id")
    val habitat_id: Int = 0,
    @ColumnInfo(name = "water_type")
    val water_type: String,
    @ColumnInfo(name = "location")
    val location: String,
    @ColumnInfo(name = "name")
    val name: String
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
    parentColumns = arrayOf("fish_id"),
    childColumns = arrayOf("id_fish"),
    onDelete = ForeignKey.NO_ACTION
)])
data class FishFact(
    @PrimaryKey
    @ColumnInfo(name = "fact_id")
    val fact_id: Int = 0,
    @ColumnInfo(name = "fact")
    val fact: String,
    @ColumnInfo(name = "id_fish")
    val id_fish: Int
)