package com.eunhye.com.android_sunflower_example.utilities

import android.app.Application
import android.content.Context
import com.eunhye.com.android_sunflower_example.data.AppDatabase
import com.eunhye.com.android_sunflower_example.data.PlantRepository
import com.eunhye.com.android_sunflower_example.viewmodels.PlantDetailViewModelFactory
import com.eunhye.com.android_sunflower_example.viewmodels.PlantListViewModelFactory

/**
 * Static methods used to inject classes needed for various Activities and Fragments.
 */
object InjectorUtils {

    private fun provideRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())
    }

    @JvmStatic fun providePlantListViewModelFactory(
        context: Context
    ): PlantListViewModelFactory {
        val repository = provideRepository(context)
        return PlantListViewModelFactory(repository)
    }

    @JvmStatic fun providePlantDetailViewModelFactory(
        context: Context,
        plantId: String
    ): PlantDetailViewModelFactory {
        val repository = provideRepository(context.applicationContext)
        return PlantDetailViewModelFactory(repository, plantId)
    }

}