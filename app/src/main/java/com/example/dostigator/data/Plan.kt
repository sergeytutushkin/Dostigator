package com.example.dostigator.data

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
    @ColumnInfo(name = "id")
    var id: Long = 0L,

    @ColumnInfo(name = "title")
    var title: String = "",

    @ColumnInfo(name = "description")
    var description: String = "",

//    val startDate: Date
//    val endDate: Date

    @ColumnInfo(name = "completed")
    var isCompleted: Boolean = false,

    @ColumnInfo(name = "goal_id", index = true)
    var goalId: Long
)
