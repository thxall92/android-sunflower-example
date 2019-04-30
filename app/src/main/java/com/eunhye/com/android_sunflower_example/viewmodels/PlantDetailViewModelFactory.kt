package com.eunhye.com.android_sunflower_example.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.eunhye.com.android_sunflower_example.data.GardenPlantingRepository
import com.eunhye.com.android_sunflower_example.data.PlantRepository

class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T
    }

}