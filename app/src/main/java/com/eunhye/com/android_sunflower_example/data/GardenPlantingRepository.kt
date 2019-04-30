package com.eunhye.com.android_sunflower_example.data

import com.eunhye.com.android_sunflower_example.utilities.runOnIoThread

class GardenPlantingRepository private constructor(
    private val gardenPlantingDao: GardenPlantingDao
) {

    fun createGardenPlanting(plantId: String) {
        runOnIoThread {
            val gardenPlanting = GardenPlanting("gp$plantId", plantId)
            gardenPlantingDao.insertGardenPlanting(gardenPlanting)
        }
    }

    fun getGardenPlantingForPlant(plantId: String) =
        gardenPlantingDao.getGardenPlantingForPlant(plantId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao) =
            instance ?: synchronized(this) {
                instance ?: GardenPlantingRepository(gardenPlantingDao).also { instance = it }
            }
    }
}