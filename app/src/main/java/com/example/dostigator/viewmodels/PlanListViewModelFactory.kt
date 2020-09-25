package com.example.dostigator.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dostigator.data.PlanDao

class PlanListViewModelFactory(
    private val goalKey: Long,
    private val dataSource: PlanDao
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlanListViewModel::class.java)) {
            return PlanListViewModel(goalKey, dataSource) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}