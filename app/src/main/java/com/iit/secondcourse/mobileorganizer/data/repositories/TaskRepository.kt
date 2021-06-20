package com.iit.secondcourse.mobileorganizer.data.repositories

import com.iit.secondcourse.mobileorganizer.data.db.dao.SubtaskDao
import com.iit.secondcourse.mobileorganizer.data.db.dao.TaskDao
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao, private val subtaskDao: SubtaskDao) {

    val allTasks: Flow<List<TaskWithSubtasks>> = taskDao.getAllTasks()

}