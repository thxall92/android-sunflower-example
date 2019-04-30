package com.eunhye.com.android_sunflower_example.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "garden_plantings",
    foreignKeys = [ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])])
data class GardenPlanting(
    @PrimaryKey @ColumnInfo(name = "id") val gardenPlantingId: String,
    @ColumnInfo(name = "plant_id") val plantId: String,
    @ColumnInfo(name = "plant_date") val plantDate: Calendar = Calendar.getInstance(),
    @ColumnInfo(name = "last_watering_date") val lastWateringDate: Calendar = Calendar.getInstance()
)