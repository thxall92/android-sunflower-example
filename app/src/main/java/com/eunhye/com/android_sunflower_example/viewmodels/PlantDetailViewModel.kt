package com.eunhye.com.android_sunflower_example.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.eunhye.com.android_sunflower_example.data.Plant
import com.eunhye.com.android_sunflower_example.data.PlantRepository

class PlantDetailViewModel(
        plantRepository: PlantRepository,
        private val plantId: String
    ) : ViewModel() {

    val plant: LiveData<Plant>

    init {
        plant = plantRepository.getPlant(plantId)
    }

}

