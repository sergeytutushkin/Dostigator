package com.example.dostigator.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "goals")
data class Goal @JvmOverloads constructor(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") var id: Long = 0,

    @ColumnInfo(name = "title") var title: String = "",
    @ColumnInfo(name = "description") var description: String = "",
    @ColumnInfo(name = "image") var imageUrl: String = "",

//    @ColumnInfo(name = "start_date") var startDate: Date = Calendar.getInstance().time,
//    @ColumnInfo(name = "end_date") var endDate: Date = startDate,

    @ColumnInfo(name = "completed") var isCompleted: Boolean = false
)