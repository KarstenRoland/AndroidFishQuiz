package com.cs364.fishquiz.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database singleton used for our Room database. Imports data from the assets/fish.dp file.
 */
@Database(entities = [Fish::class, FishFact::class, Habitat::class], version = 4, exportSchema = false)
abstract class FishDatabase : RoomDatabase() {
    abstract fun fishDao() : FishDao

    //singleton
    companion object{
        private var instance: FishDatabase? = null
        fun getInstance(context: Context): FishDatabase{
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    FishDatabase::class.java,
                    "fishy_db"
                )
                    .createFromAsset("fish.db") //load database from asset
                    .build()
            }

            return instance as FishDatabase
        }
    }
}