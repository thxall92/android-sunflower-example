package com.eunhye.com.android_sunflower_example.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.eunhye.com.android_sunflower_example.utilities.DATABASE_NAME
import com.eunhye.com.android_sunflower_example.utilities.runOnIoThread

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
        // TODO / STOPSHIP: This is only used for development; remove prior to shipping
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
                        runOnIoThread { seedDatabase(getInstance(context)) }
                    }
                })
                .build()
        }

        private fun seedDatabase(database: AppDatabase) {
            database.plantDao().insertAll(ArrayList<Plant>(4).apply {
                add(Plant("1", "Apple", "A red fruit", 1))
                add(Plant("2", "Beet", "A red root vegetable", 1))
                add(Plant("3", "Celery", "A green vegetable", 2))
                add(Plant("4", "Tomato", "A red vegetable", 3))
            })
        }
    }

}