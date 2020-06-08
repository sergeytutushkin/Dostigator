package com.example.dostigator

import android.app.Application
import androidx.room.Room
import com.example.dostigator.database.source.local.DostigatorDatabase

class DostigatorApp : Application() {

    private lateinit var instance: DostigatorApp
    private lateinit var database: DostigatorDatabase

    override fun onCreate() {
        super.onCreate()
        instance = this
        database = Room.databaseBuilder(this, DostigatorDatabase::class.java, "database")
            .allowMainThreadQueries()
            .build()
    }

    fun getAppInstance(): DostigatorApp {
        return instance
    }

    fun getDatabase(): DostigatorDatabase {
        return database
    }
}