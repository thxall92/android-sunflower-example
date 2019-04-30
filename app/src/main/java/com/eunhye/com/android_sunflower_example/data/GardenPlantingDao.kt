package com.eunhye.com.android_sunflower_example.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GardenPlantingDao {
    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId")
    fun getGardenPlanting(gardenPlantingId: String): LiveData<GardenPlanting>

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    fun getGardenPlantingForPlant(plantId: String): LiveData<GardenPlanting>

    @Insert
    fun insertGardenPlanting(gardenPlanting: GardenPlanting)
}