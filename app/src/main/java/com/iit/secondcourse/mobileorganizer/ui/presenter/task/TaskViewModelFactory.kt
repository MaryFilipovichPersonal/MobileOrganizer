package com.iit.secondcourse.mobileorganizer.ui.presenter.task

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iit.secondcourse.mobileorganizer.data.repositories.TaskRepository
import java.lang.IllegalArgumentException

class TaskViewModelFactory (private val repository: TaskRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TaskViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TaskViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}