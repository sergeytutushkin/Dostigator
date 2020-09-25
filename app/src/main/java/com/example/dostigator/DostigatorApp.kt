package com.example.dostigator

import android.app.Application
import androidx.room.Room
import com.example.dostigator.data.AppDatabase

// TODO Delete?
class DostigatorApp : Application() {

    private lateinit var instance: DostigatorApp
    private lateinit var database: AppDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, AppDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getAppInstance(): DostigatorApp {
        return instance
    }

    fun getDatabase(): AppDatabase {
        return database
    }
}