package com.example.dostigator.database

import androidx.room.*

@Dao
interface GoalDao {
    @Query("SELECT * FROM goals")
    fun getAll(): List<Goal>

    @Query("SELECT * FROM goals WHERE id=:id")
    fun getById(id: Long): Goal

    @Query("DELETE FROM goals")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(goal: Goal): Long

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(goal: Goal)

    @Delete
    fun delete(goal: Goal)
}