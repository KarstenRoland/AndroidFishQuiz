package com.cs364.fishquiz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Fish::class, FishFact::class, Habitat::class], version = 1, exportSchema = false)
abstract class FishDatabase : RoomDatabase() {
    abstract fun fishDao() : FishDao

    companion object{
        private var instance: FishDatabase? = null

        fun getInstance(context: Context): FishDatabase{
            //check if null and build it if it is null.
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context,
                    FishDatabase::class.java,
                    "fish_db"
                )
                    .createFromAsset("")    //load the database
                    .build()
            }

            //return the instane as cast to FishDatabase
            return instance as FishDatabase
        }
    }
}