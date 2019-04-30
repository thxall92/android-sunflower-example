package com.eunhye.com.android_sunflower_example.utilities

import android.content.Context
import com.eunhye.com.android_sunflower_example.data.AppDatabase
import com.eunhye.com.android_sunflower_example.data.GardenPlantingRepository
import com.eunhye.com.android_sunflower_example.data.PlantRepository
import com.eunhye.com.android_sunflower_example.viewmodels.PlantDetailViewModelFactory
import com.eunhye.com.android_sunflower_example.viewmodels.PlantListViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context).plantDao())
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(AppDatabase.getInstance(context).gardenPlantingDao())
    }

    fun providePlantListViewModelFactory(context: Context): PlantListViewModelFactory {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }

    fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(getPlantRepository(context),
            getGardenPlantingRepository(context), plantId)
    }

}