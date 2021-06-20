package com.iit.secondcourse.mobileorganizer.ui.presenter.task

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.repositories.TaskRepository

class TaskViewModel(private val repository: TaskRepository): ViewModel() {

    val allTasks: LiveData<List<TaskWithSubtasks>> = repository.allTasks.asLiveData()

}