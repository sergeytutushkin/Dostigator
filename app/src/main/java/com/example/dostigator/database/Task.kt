package com.example.dostigator.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "tasks",
    foreignKeys = [
        ForeignKey(
            entity = Plan::class,
            parentColumns = ["id"],
            childColumns = ["plan_id"],
            onDelete = ForeignKey.CASCADE
        )]
)
data class Task @JvmOverloads constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,

    var title: String = "",
    var description: String = "",

//    val startDate: Date
//    val endDate: Date

    @ColumnInfo(name = "completed") var isCompleted: Boolean = false,

    @ColumnInfo(name = "plan_id", index = true) var planId: Long
)
