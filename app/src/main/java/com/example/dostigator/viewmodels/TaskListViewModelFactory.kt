package com.example.dostigator.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.data.TaskDao

class TaskListViewModelFactory(
    private val planKey: Long,
    private val dataSource: TaskDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskListViewModel::class.java)) {
            return TaskListViewModel(planKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}