package com.example.dostigator.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.data.Task
import com.example.dostigator.data.TaskDao
import kotlinx.coroutines.*
import kotlin.random.Random

class TaskListViewModel(
    private val planKey: Long,
    private val dataSource: TaskDao
) : ViewModel() {

    private var viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    // TODO Save planKey

    private val _tasks = MutableLiveData<List<Task>>()
    val tasks: LiveData<List<Task>>
        get() = _tasks

    private val _clickTaskList = MutableLiveData<Long>()
    val clickTaskList
        get() = _clickTaskList

    fun onTaskListClicked(id: Long) {
        _clickTaskList.value = id
    }

    init {
        uiScope.launch {
            clear() // TODO Remove later

            for (i in 1..10) {  // TODO Remove later
                insert(
                    Task(
                        id = i.toLong(),
                        title = "Task $i",
                        description = "This is task $i",
                        isCompleted = Random.nextBoolean(),
                        planId = 2
                    )
                )
            }

            _tasks.value = getTasksFromDatabaseById(planKey)
        }
    }

    private suspend fun clear() {
        withContext(Dispatchers.IO) {
            this@TaskListViewModel.dataSource.clear()
        }
    }

    private suspend fun insert(task: Task) {
        withContext(Dispatchers.IO) {
            println("insert ${task.title}")
            this@TaskListViewModel.dataSource.insert(task)
        }
    }

    private suspend fun getTasksFromDatabaseById(id: Long): List<Task>? {
        return withContext(Dispatchers.IO) {
            this@TaskListViewModel.dataSource.getByPlanId(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
