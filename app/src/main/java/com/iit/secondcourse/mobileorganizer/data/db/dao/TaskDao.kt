package com.iit.secondcourse.mobileorganizer.data.db.dao

import androidx.room.*
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Transaction
    @Query ("SELECT * FROM TASK_TABLE")
    fun getAllTasks(): Flow<List<TaskWithSubtasks>>

    @Transaction
    @Query("SELECT * FROM TASK_TABLE WHERE id=:id")
    suspend fun getTaskById(id: Long): TaskWithSubtasks

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(task: Task)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTasks(tasks: List<Task>)

    @Query("DELETE FROM task_table")
    suspend fun deleteAllTasks()

}