package com.cs364.fishquiz.ui.data

import kotlinx.coroutines.flow.Flow

/**
 * The repository singleton for the fish database. Inherits from the FishInterface to force
 * the dao functions.
 *
 * @param fishDao the daos we're getting data from.
 */
class FishRepository(private val fishDao: FishDao) : FishInterface {
    //query daos
    override fun getAllFacts(): Flow<List<FishFact>> = fishDao.getAllFacts()

    override fun getAllFish(): Flow<List<Fish>> = fishDao.getAllFish()

    override fun getAllHabs(): Flow<List<Habitat>> = fishDao.getAllHabs()

    override fun getFishFromId(id: Int): Flow<Fish> = fishDao.getFishFromId(id)

    override fun getFishFromName(name: String): Flow<Fish> = fishDao.getFishFromName(name)

    override fun getAllFishInHabitat(habitat: String): Flow<List<Fish>> = fishDao.getAllFishInHabitat(habitat)


    //singleton format
    companion object {
        private var repository: FishInterface? = null
        fun getRepo(fishDb: FishDatabase):
                FishInterface {
            if (repository == null) {
                repository = FishRepository(fishDb.fishDao())
            }

            return repository!!
        }
    }
}