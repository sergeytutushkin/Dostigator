package com.example.dostigator.database

import androidx.room.*

@Dao
interface PlanDao {
    @Query("SELECT * FROM plans")
    fun getAll(): List<Plan>

    @Query("SELECT * FROM plans WHERE id=:id")
    fun getById(id: Long): Plan

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(plan: Plan): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(plan: Plan)

    @Delete
    fun delete(plan: Plan)
}