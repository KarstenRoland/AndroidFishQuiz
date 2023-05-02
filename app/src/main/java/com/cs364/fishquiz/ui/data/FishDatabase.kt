package com.cs364.fishquiz.ui.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database singleton used for our Room database. Imports data from the assets/fish.dp file.
 */
@Database(entities = [Fish::class, FishFact::class, Habitat::class], version = 9, exportSchema = false)
abstract class FishDatabase : RoomDatabase() {
    abstract fun fishDao() : FishDao

    //singleton
    companion object{
        @Volatile
        private var instance: FishDatabase? = null
        fun getInstance(context: Context): FishDatabase{
            return instance ?: synchronized(this) {
                Room.databaseBuilder(context, FishDatabase::class.java, "fishy_db")
                    .fallbackToDestructiveMigration()
                    .createFromAsset("fish.db") //prepopulate the database
                    .build()
                    .also { instance = it }
            }
        }
    }
}