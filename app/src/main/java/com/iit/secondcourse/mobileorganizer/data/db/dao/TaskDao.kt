package com.iit.secondcourse.mobileorganizer.data.db.dao

import androidx.room.*
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import com.iit.secondcourse.mobileorganizer.utils.TASK_DATE_DEADLINE
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {

    @Transaction
    @Query("SELECT * FROM TASK_TABLE ORDER BY $TASK_DATE_DEADLINE DESC")
    fun getAllTasks(): Flow<List<TaskWithSubtasks>>

    @Transaction
    @Query("SELECT * FROM TASK_TABLE WHERE id=:id")
    suspend fun getTaskById(id: Long): TaskWithSubtasks

}