package com.example.dostigator.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Goal::class, Plan::class, Task::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val planDao: PlanDao
    abstract val taskDao: TaskDao

    companion object {

        @Volatile
        private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = instance

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "dostigator_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()   // TODO Delete after replacing coroutines
                        .build()
                    Companion.instance = instance
                }
                return instance
            }
        }
    }
}