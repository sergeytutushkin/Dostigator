package com.example.dostigator.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.data.GoalDao

class GoalListViewModelFactory(
    private val dataSource: GoalDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalListViewModel::class.java)) {
            return GoalListViewModel(dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}