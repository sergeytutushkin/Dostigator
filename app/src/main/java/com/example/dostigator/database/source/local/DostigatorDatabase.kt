package com.example.dostigator.database.source.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dostigator.database.*

@Database(entities = [Goal::class, Plan::class, Task::class], version = 3)
abstract class DostigatorDatabase : RoomDatabase() {
    abstract val goalDao: GoalDao
    abstract val planDao: PlanDao
    abstract val taskDao: TaskDao

    companion object {

        @Volatile
        private var INSTANCE: DostigatorDatabase? = null

        fun getInstance(context: Context): DostigatorDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        DostigatorDatabase::class.java,
                        "dostigator_database"
                    )
                        .fallbackToDestructiveMigration()
                        .allowMainThreadQueries()   // TODO Delete after replacing coroutines
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}