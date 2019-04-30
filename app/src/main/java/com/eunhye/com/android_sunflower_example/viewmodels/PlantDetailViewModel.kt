package com.eunhye.com.android_sunflower_example.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.eunhye.com.android_sunflower_example.data.GardenPlantingRepository
import com.eunhye.com.android_sunflower_example.data.Plant
import com.eunhye.com.android_sunflower_example.data.PlantRepository

class PlantDetailViewModel(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    val isPlanted: LiveData<Boolean>
    val plant: LiveData<Plant>

    init {
        val gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId)

        //check empty
        isPlanted = Transformations.map(gardenPlantingForPlant) { it != null }

        plant = plantRepository.getPlant(plantId)
    }

    fun addPlantToGarden() {
        gardenPlantingRepository.createGardenPlanting(plantId)
    }

}

