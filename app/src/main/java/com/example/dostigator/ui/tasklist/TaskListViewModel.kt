package com.example.dostigator.ui.tasklist

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dostigator.database.Task
import com.example.dostigator.database.TaskDao
import kotlinx.coroutines.*
import kotlin.random.Random

class TaskListViewModel(
    private val planKey: Long,
    dataSource: TaskDao
) : ViewModel() {

    val database = dataSource

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
            database.clear()
        }
    }

    private suspend fun insert(task: Task) {
        withContext(Dispatchers.IO) {
            println("insert ${task.title}")
            database.insert(task)
        }
    }

    private suspend fun getTasksFromDatabaseById(id: Long): List<Task>? {
        return withContext(Dispatchers.IO) {
            database.getByPlanId(id)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
}
