package com.example.dostigator.ui.plans

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.database.Plan

class PlanListViewModel : ViewModel() {

    // TODO Replace with real data from the database and rename
    private val _testList = MutableLiveData<List<Plan>>()
    val testList: LiveData<List<Plan>>
        get() = _testList

    private val _navigateToTaskList = MutableLiveData<Long>()
    val navigateToTaskList
        get() = _navigateToTaskList

    fun onPlanListClicked(id: Long) {
        _navigateToTaskList.value = id
    }

    fun onTaskListNavigated() {
        _navigateToTaskList.value = null
    }

    init {
        _testList.value = listOf(
            Plan(id = 1, title = "Plan 1", description = "This is plan 1", isCompleted = false, goalId = 1),
            Plan(id = 2, title = "Plan 2", description = "This is plan 2", isCompleted = true, goalId = 1),
            Plan(id = 3, title = "Plan 3", description = "This is plan 3", isCompleted = false, goalId = 1),
            Plan(id = 4, title = "Plan 4", description = "This is plan 4", isCompleted = false, goalId = 2),
            Plan(id = 5, title = "Plan 5", description = "This is plan 5", isCompleted = true, goalId = 2),
            Plan(id = 6, title = "Plan 6", description = "This is plan 6", isCompleted = false, goalId = 2),
            Plan(id = 7, title = "Plan 7", description = "This is plan 7", isCompleted = false, goalId = 3),
            Plan(id = 8, title = "Plan 8", description = "This is plan 8", isCompleted = true, goalId = 3),
            Plan(id = 9, title = "Plan 9", description = "This is plan 9", isCompleted = false, goalId = 3),
            Plan(id = 10, title = "Plan 10", description = "This is plan 10", isCompleted = true, goalId = 4),
            Plan(id = 11, title = "Plan 11", description = "This is plan 11", isCompleted = false, goalId = 4),
            Plan(id = 12, title = "Plan 12", description = "This is plan 12", isCompleted = true, goalId = 4),
            Plan(id = 13, title = "Plan 13", description = "This is plan 13", isCompleted = false, goalId = 5),
            Plan(id = 14, title = "Plan 14", description = "This is plan 14", isCompleted = true, goalId = 5),
            Plan(id = 15, title = "Plan 15", description = "This is plan 15", isCompleted = false, goalId = 5)
        )
    }
}
