package com.example.dostigator.ui.goals

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.database.Goal

class GoalListViewModel : ViewModel() {

    // TODO Replace with real data from the database and rename
    private val _testList = MutableLiveData<List<Goal>>()
    val testList: LiveData<List<Goal>>
        get() = _testList

    private val _navigateToPlanList = MutableLiveData<Long>()
    val navigateToPlanList
        get() = _navigateToPlanList

    fun onGoalListClicked(id: Long) {
        _navigateToPlanList.value = id
    }

    fun onPlanListNavigated() {
        _navigateToPlanList.value = null
    }

    init {
        _testList.value = listOf(
            Goal(id = 1, title = "Goal 1", description = "This is goal 1", isCompleted = false),
            Goal(id = 2, title = "Goal 2", description = "This is goal 2", isCompleted = true),
            Goal(id = 3, title = "Goal 3", description = "This is goal 3", isCompleted = false),
            Goal(id = 4, title = "Goal 4", description = "This is goal 4", isCompleted = false),
            Goal(id = 5, title = "Goal 5", description = "This is goal 5", isCompleted = true)
//            Goal(id = 6, title = "Goal 6", description = "This is goal 6", isCompleted = false),
//            Goal(id = 7, title = "Goal 7", description = "This is goal 7", isCompleted = false),
//            Goal(id = 8, title = "Goal 8", description = "This is goal 8", isCompleted = true),
//            Goal(id = 9, title = "Goal 9", description = "This is goal 9", isCompleted = false),
//            Goal(id = 10, title = "Goal 10", description = "This is goal 10", isCompleted = true),
//            Goal(id = 11, title = "Goal 11", description = "This is goal 11", isCompleted = false),
//            Goal(id = 12, title = "Goal 12", description = "This is goal 12", isCompleted = true),
//            Goal(id = 13, title = "Goal 13", description = "This is goal 13", isCompleted = false),
//            Goal(id = 14, title = "Goal 14", description = "This is goal 14", isCompleted = true),
//            Goal(id = 15, title = "Goal 15", description = "This is goal 15", isCompleted = false)
        )
    }
}
