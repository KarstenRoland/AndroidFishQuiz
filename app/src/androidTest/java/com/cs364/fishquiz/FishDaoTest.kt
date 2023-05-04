package com.cs364.fishquiz

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cs364.fishquiz.ui.data.*
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Class to test the functionality of the room prepackaged database.
 */
@RunWith(AndroidJUnit4::class)
class FishDaoTest {
    private lateinit var fishDao: FishDao
    private lateinit var fishData: FishDatabase

    /**
     * Sets up the database and dao for testing
     */
    @Before
    fun databaseSetup() {
        val context: Context = ApplicationProvider.getApplicationContext()

        fishData = Room.databaseBuilder(context, FishDatabase::class.java, "testDB")
            .allowMainThreadQueries()
            .createFromAsset("fish.db")
            .build()

        fishDao = fishData.fishDao()
    }

    /**
     * Closes the database after testing is done.
     */
    @After
    fun close() {
        fishData.close()
    }

    /**
     * Testing that there is prepopulated data in the database.
     */
    @Test
    fun daoGetAllEntries() = runBlocking {
        //values of what should be in the database
        val NUM_OF_FISH: Int = 23
        val NUM_OF_HABITATIS: Int = 10
        val NUM_OF_FACTS: Int = 1

        //get the lists from the Dao
        val allFish = fishDao.getAllFish().first()
        val allHabs = fishDao.getAllHabs().first()
        val allFacts = fishDao.getAllFacts().first()

        Assert.assertEquals(allFish.size, NUM_OF_FISH)
        Assert.assertEquals(allHabs.size, NUM_OF_HABITATIS)
        Assert.assertEquals(allFacts.size, NUM_OF_FACTS)
    }

    /**
     * Testing getting a single entity from the database when using these daos.
     */
    @Test
    fun daoGetSpecificEntry() = runBlocking {
        val myFish = Fish(
            4,
            "Pufferfish",
            "Tetraodontidae",
            "Tetraodontidae",
            1.5,
            0.3,
            9,
            "Pufferfish are a group of fish that are known for their ability to inflate themselves into a ball-like shape when threatened. They have a poisonous toxin in their skin and internal organs, making them potentially deadly if not prepared correctly.",
            2
        )
        val myHabitat = Habitat(
            9,
            "Fresh",
            "North America",
            "Mississippi River"
        )
        val FAIL_ID = 100

        val gotFish = fishDao.getFishFromId(myFish.fish_id).first()
        val gotHab = fishDao.getHabitatFromId(myHabitat.habitat_id).first()
        val failFish = fishDao.getFishFromId(FAIL_ID).first()
        val failHab = fishDao.getHabitatFromId(FAIL_ID).first()

        Assert.assertEquals(myFish, gotFish)
        Assert.assertEquals(myHabitat, gotHab)
        Assert.assertNull(failFish)
        Assert.assertNull(failHab)
    }

    /**
     * Testing that we get a list of fish that should be in one of the
     * three tabs: Salty, Fresh, and Brackish.
     */
    @Test
    fun getTabbedFish() = runBlocking {
        val FRESH = "Fresh"
        val SALTY = "Salty"
        val BRACKISH = "Brackish"
        val TOTAL_FISH = fishDao.getAllFish().first().size
        val gotFresh = fishDao.getAllFishInHabitat(FRESH).first()
        val gotSalty = fishDao.getAllFishInHabitat(SALTY).first()
        val gotBrack = fishDao.getAllFishInHabitat(BRACKISH).first()

        Assert.assertTrue(gotFresh.size < TOTAL_FISH && gotFresh.isNotEmpty())
        Assert.assertTrue(gotSalty.size < TOTAL_FISH && gotSalty.isNotEmpty())
        Assert.assertTrue(gotBrack.size < TOTAL_FISH && gotBrack.isNotEmpty())
        Assert.assertEquals(gotFresh.size + gotSalty.size + gotBrack.size, TOTAL_FISH)

        gotFresh.forEach { fish ->
            Assert.assertEquals(fishDao.getHabitatFromId(fish.hab_id).first().water_type, FRESH)
        }
        gotSalty.forEach { fish ->
            Assert.assertEquals(fishDao.getHabitatFromId(fish.hab_id).first().water_type, SALTY)
        }
        gotBrack.forEach { fish ->
            Assert.assertEquals(fishDao.getHabitatFromId(fish.hab_id).first().water_type, BRACKISH)
        }
    }
}