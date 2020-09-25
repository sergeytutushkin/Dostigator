package com.example.dostigator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.data.Goal
import com.example.dostigator.data.GoalDao
import kotlinx.coroutines.*

class GoalListViewModel(
    private val dataSource: GoalDao
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _goals = MutableLiveData<List<Goal>>()
    val goals: LiveData<List<Goal>>
        get() = _goals

    private val _navigateToPlanList = MutableLiveData<Long>()
    val navigateToPlanList
        get() = _navigateToPlanList

    fun onGoalListClicked(id: Long) {
        _navigateToPlanList.value = id
    }

    fun onPlanListNavigated() {
        _navigateToPlanList.value = null
    }

    private val _navigateToGoalEdit = MutableLiveData<Long>()
    val navigateToGoalEdit
        get() = _navigateToGoalEdit

    fun onGoalEditClicked(id: Long) {
        _navigateToGoalEdit.value = id
    }

    fun onGoalEditNavigated() {
        _navigateToGoalEdit.value = null
    }

    init {
        uiScope.launch {
//            clear() // TODO Remove later
//
//            for (i in 1..10) {  // TODO Remove later
//                insert(
//                    Goal(
//                        id = i.toLong(),
//                        title = "Goal $i",
//                        description = "This is goal $i",
//                        isCompleted = Random.nextBoolean()
//                    )
//                )
//            }

            _goals.value = getGoalsFromDatabase()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            println("clear")
            dataSource.clear()
        }
    }

    private suspend fun insert(goal: Goal) {
        withContext(Dispatchers.IO) {
            println("insert ${goal.title}")
            dataSource.insert(goal)
        }
    }

    private suspend fun getGoalsFromDatabase(): List<Goal>? {
        return withContext(Dispatchers.IO) {
            println("get")
            dataSource.getAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}
