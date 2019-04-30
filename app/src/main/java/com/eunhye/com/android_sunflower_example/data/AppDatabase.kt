package com.eunhye.com.android_sunflower_example.data

import android.content.Context
import android.util.Log
import androidx.annotation.VisibleForTesting
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eunhye.com.android_sunflower_example.utilities.DATABASE_NAME
import com.eunhye.com.android_sunflower_example.utilities.runOnIoThread
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.nio.charset.Charset

@Database(entities = [Plant::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun plantDao(): PlantDao

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: deleteAndBuildDatabase(context).also { instance = it }
            }
        }

        // Reset the database to have new data on every app launch
        private fun deleteAndBuildDatabase(context: Context): AppDatabase {
            context.deleteDatabase(DATABASE_NAME)
            return buildDatabase(context)
        }

        // Create and pre-populate the database. See this article for more details:
        // https://medium.com/google-developers/7-pro-tips-for-room-fbadea4bfbd1#4785
        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        runOnIoThread { seedDatabase(context) }
                    }
                })
                .build()
        }

        private fun seedDatabase(context: Context) {
            val plantType = object: TypeToken<List<Plant>>(){}.type
            val plantList: List<Plant> = Gson().fromJson(readJson(context), plantType)
            val database = getInstance(context)
            database.plantDao().insertAll(plantList)
        }

        @VisibleForTesting internal fun readJson(
            context: Context,
            fileName: String = "plants.json"
        ): String {
            return try {
                val inputStream = context.assets.open(fileName)
                val buffer = ByteArray(inputStream.available())
                inputStream.run{
                    read(buffer)
                    close()
                }
                String(buffer, Charset.defaultCharset())
            }catch(ex: IOException){
                Log.i("AppDatabase", "Error reading JSON: $ex")
                ""
            }
        }
    }

}