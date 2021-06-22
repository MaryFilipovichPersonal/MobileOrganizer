package com.iit.secondcourse.mobileorganizer.ui.presenter.task

import androidx.lifecycle.*
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import com.iit.secondcourse.mobileorganizer.data.repositories.TaskRepository
import kotlinx.coroutines.launch
import java.util.*

class TaskViewModel(private val repository: TaskRepository) : ViewModel() {

    val allTasks: LiveData<List<TaskWithSubtasks>> = repository.allTasks.asLiveData()

    private val _isInserted = MutableLiveData(false)
    val isInserted: LiveData<Boolean> get() = _isInserted

    fun getTasks() = allTasks.value

    fun insertTask(title: String, content: String, dateStart: Calendar, dateDeadline:Calendar, subtasks: List<Subtask>) = viewModelScope.launch {
        val task = Task(
            title,
            content,
            dateStart,
            dateDeadline
        )
        repository.insertTask(task, subtasks)
        _isInserted.value = true
    }

    fun deleteTask(task: TaskWithSubtasks) = viewModelScope.launch {
        repository.deleteTask(task)
    }

}