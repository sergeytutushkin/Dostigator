package com.example.dostigator.ui.goallist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.dostigator.database.Goal
import com.example.dostigator.database.GoalDao
import kotlinx.coroutines.*
import kotlin.random.Random

class GoalListViewModel(
    val database: GoalDao,
    application: Application
) : AndroidViewModel(application) {

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

    init {
        uiScope.launch {
            clear() // TODO Remove later

            for (i in 1..10) {  // TODO Remove later
                insert(
                    Goal(
                        id = i.toLong(),
                        title = "Goal $i",
                        description = "This is goal $i",
                        isCompleted = Random.nextBoolean()
                    )
                )
            }

            _goals.value = getGoalsFromDatabase()
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            println("clear")
            database.clear()
        }
    }

    private suspend fun insert(goal: Goal) {
        withContext(Dispatchers.IO) {
            println("insert ${goal.title}")
            database.insert(goal)
        }
    }

    private suspend fun getGoalsFromDatabase(): List<Goal>? {
        return withContext(Dispatchers.IO) {
            println("get")
            database.getAll()
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
