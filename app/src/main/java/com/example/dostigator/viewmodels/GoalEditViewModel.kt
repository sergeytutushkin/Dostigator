package com.example.dostigator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.data.Goal
import com.example.dostigator.data.GoalDao
import kotlinx.coroutines.*

class GoalEditViewModel(
    private val goalKey: Long,
    private val dataSource: GoalDao,
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private val _goal = MutableLiveData(Goal())
    val goal: LiveData<Goal> = _goal

    private val _navigateToGoalList = MutableLiveData<Boolean?>()
    val navigateToGoalList: LiveData<Boolean?> = _navigateToGoalList

    init {
        if (goalKey != 0L) {
            uiScope.launch {
                _goal.value = load(goalKey)
            }
        }
    }

    private suspend fun load(id: Long): Goal? {
        return withContext(Dispatchers.IO) {
            dataSource.getById(id)
        }
    }

    fun doneNavigating() {
        _navigateToGoalList.value = null
    }

    fun onSaveGoal() {
        uiScope.launch {
            insert(_goal.value)

            _navigateToGoalList.value = true
        }
    }

    private suspend fun insert(goal: Goal?) {
        withContext(Dispatchers.IO) {
            if (goal != null) {
//                println("insert ${goal.title}")
                dataSource.insert(goal)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
