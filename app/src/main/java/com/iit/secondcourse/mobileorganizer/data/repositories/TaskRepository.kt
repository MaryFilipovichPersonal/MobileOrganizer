package com.iit.secondcourse.mobileorganizer.data.repositories

import androidx.annotation.WorkerThread
import com.iit.secondcourse.mobileorganizer.data.db.dao.SubtaskDao
import com.iit.secondcourse.mobileorganizer.data.db.dao.TaskDao
import com.iit.secondcourse.mobileorganizer.data.db.dao.TaskSubtaskDao
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao, private val subtaskDao: SubtaskDao, private val taskSubtaskDao: TaskSubtaskDao) {

    val allTasks: Flow<List<TaskWithSubtasks>> = taskDao.getAllTasks()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getTaskById(id: Long) = taskDao.getTaskById(id)

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertTask(task: TaskWithSubtasks) {
        taskSubtaskDao.insertTask(task)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateTask(task: TaskWithSubtasks) {
        taskSubtaskDao.updateTask(task)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteTask(task: TaskWithSubtasks) {
        taskSubtaskDao.deleteTaskWithSubtasks(task)
    }

}