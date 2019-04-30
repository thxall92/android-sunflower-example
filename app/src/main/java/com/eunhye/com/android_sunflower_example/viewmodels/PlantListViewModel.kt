package com.eunhye.com.android_sunflower_example.viewmodels

import androidx.lifecycle.*
import com.eunhye.com.android_sunflower_example.data.Plant
import com.eunhye.com.android_sunflower_example.data.PlantRepository

/**
 * The ViewModel for PlantListActivity.
 */
class PlantListViewModel internal constructor(plantRepository: PlantRepository) : ViewModel() {

    private val NO_GROW_ZONE = -1
    private val growZoneNumber: MutableLiveData<Int> = MutableLiveData()

    private val plantList = MediatorLiveData<List<Plant>>()

    init {
        growZoneNumber.value = NO_GROW_ZONE

        val livePlantList = Transformations.switchMap(growZoneNumber) {
            if (it == NO_GROW_ZONE) {
                plantRepository.getPlants()
            } else {
                plantRepository.getPlantsWithGrowZoneNumber(it)
            }
        }
        plantList.addSource(livePlantList, plantList::setValue)
    }

    fun getPlants() = plantList

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE
}
