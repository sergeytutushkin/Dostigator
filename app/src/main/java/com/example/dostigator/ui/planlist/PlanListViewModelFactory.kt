package com.example.dostigator.ui.planlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.database.PlanDao

class PlanListViewModelFactory(
    private val goalKey: Long,
    private val dataSource: PlanDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanListViewModel::class.java)) {
            return PlanListViewModel(goalKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}