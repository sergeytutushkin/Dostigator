package com.example.dostigator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "plans",
    foreignKeys = [
        ForeignKey(
            entity = Goal::class,
            parentColumns = ["id"],
            childColumns = ["goal_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class Plan @JvmOverloads constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,

    var title: String = "",
    var description: String = "",

//    val startDate: Date
//    val endDate: Date

    @ColumnInfo(name = "completed") var isCompleted: Boolean = false,

    @ColumnInfo(name = "goal_id", index = true) var goalId: Long
)
