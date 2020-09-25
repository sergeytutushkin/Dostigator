package com.example.dostigator.data

import androidx.room.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM tasks")
    fun getAll(): List<Task>

    @Query("SELECT * FROM tasks WHERE id=:id")
    fun getById(id: Long): Task

    @Query("SELECT * FROM tasks WHERE plan_id=:id")
    fun getByPlanId(id: Long): List<Task>

    @Query("DELETE FROM tasks")
    fun clear()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(task: Task)

    @Delete
    fun delete(task: Task)
}