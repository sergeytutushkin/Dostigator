package com.example.dostigator.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.data.GoalDao

class GoalEditViewModelFactory(
    private val goalKey: Long,
    private val dataSource: GoalDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GoalEditViewModel::class.java)) {
            return GoalEditViewModel(goalKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}