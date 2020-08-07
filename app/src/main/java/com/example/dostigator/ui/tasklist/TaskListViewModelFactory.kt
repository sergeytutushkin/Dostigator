package com.example.dostigator.ui.tasklist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.database.TaskDao

class TaskListViewModelFactory(
    private val planKey: Long,
    private val dataSource: TaskDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(planKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}