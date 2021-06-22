package com.iit.secondcourse.mobileorganizer.data.db.dao

import androidx.room.*
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.data.entities.Task

@Dao
abstract class TaskSubtaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTask(task: Task): Long

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTasks(tasks: List<Task>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertSubtask(subtask: Subtask)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertSubtasks(subtasks: List<Subtask>)

    @Query("SELECT id FROM task_table WHERE rowId = :rowId")
    abstract suspend fun getTaskId(rowId: Long): Long

    @Transaction
    open suspend fun insertTaskWithSubtasks(task: Task, subtasks: List<Subtask>){
        val rowId: Long = insertTask(task)
        val id: Long = getTaskId(rowId)
        val dbSubtasks = arrayListOf<Subtask>()
        subtasks.forEach {
            dbSubtasks.add(Subtask(id, it.title, it.description, it.isCompleted))
        }
        insertSubtasks(dbSubtasks)
    }

    @Update
    abstract suspend fun updateTask(task: Task)

    @Update
    abstract suspend fun updateSubtask(subtask: Subtask)

    @Transaction
    open suspend fun updateTask(task: TaskWithSubtasks) {
        task.subtasks.forEach {
            updateSubtask(it)
        }
        updateTask(task.task)
    }

    @Delete
    abstract suspend fun deleteTask(task: Task)

    @Delete
    abstract suspend fun deleteSubtask(subtask: Subtask)

    @Transaction
    open suspend fun deleteTaskWithSubtasks(task: TaskWithSubtasks) {
        task.subtasks.forEach {
            deleteSubtask(it)
        }
        deleteTask(task.task)
    }

    @Query("DELETE FROM task_table")
    abstract suspend fun deleteAllTasks()

    @Query("DELETE FROM subtask_table")
    abstract suspend fun deleteAllSubtasks()

    @Transaction
    open suspend fun deleteAllTasksSubtasks() {
        deleteAllSubtasks()
        deleteAllTasks()
    }

}