package com.example.dostigator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.data.Plan
import com.example.dostigator.data.PlanDao
import kotlinx.coroutines.*
import kotlin.random.Random

class PlanListViewModel(
    private val goalKey: Long,
    private val dataSource: PlanDao
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // TODO Save goalKey

    private val _plans = MutableLiveData<List<Plan>>()
    val plans: LiveData<List<Plan>>
        get() = _plans

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
        uiScope.launch {
            clear() // TODO Remove later

            for (i in 1..10) {  // TODO Remove later
                insert(
                    Plan(
                        id = i.toLong(),
                        title = "Plan $i",
                        description = "This is plan $i",
                        isCompleted = Random.nextBoolean(),
                        goalId = 1
                    )
                )
            }

            _plans.value = getPlansFromDatabaseById(goalKey)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            dataSource.clear()
        }
    }

    private suspend fun insert(plan: Plan) {
        withContext(Dispatchers.IO) {
            println("insert ${plan.title}")
            dataSource.insert(plan)
        }
    }

    private suspend fun getPlansFromDatabaseById(id: Long): List<Plan>? {
        return withContext(Dispatchers.IO) {
            dataSource.getByGoalId(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
