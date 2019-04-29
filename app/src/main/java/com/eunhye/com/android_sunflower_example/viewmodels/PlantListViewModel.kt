package com.eunhye.com.android_sunflower_example.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.eunhye.com.android_sunflower_example.data.Plant
import com.eunhye.com.android_sunflower_example.data.PlantRepository

/**
 * The ViewModel for PlantListActivity.
 */
class PlantListViewModel internal constructor(plantRepository: PlantRepository) : ViewModel() {

    private val NO_GROW_ZONE = -1
    private val plantRepo: PlantRepository = plantRepository
    private val growZoneNumber: MutableLiveData<Int> = MutableLiveData()

    init {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun getPlants(): LiveData<List<Plant>> = Transformations.switchMap(growZoneNumber) {
        if (it == NO_GROW_ZONE) {
            plantRepo.getPlants()
        } else {
            plantRepo.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }
}
