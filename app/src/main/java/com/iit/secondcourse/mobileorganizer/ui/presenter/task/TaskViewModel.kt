package com.iit.secondcourse.mobileorganizer.ui.presenter.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.repositories.TaskRepository
import kotlinx.coroutines.launch

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val allTasks: LiveData<List<TaskWithSubtasks>> = repository.allTasks.asLiveData()

    fun deleteTask(task: TaskWithSubtasks) = viewModelScope.launch {
        repository.deleteTask(task)
    }

    fun getTasks() = allTasks.value

}