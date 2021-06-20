package com.iit.secondcourse.mobileorganizer.data.db.dao

import androidx.room.*
import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import kotlinx.coroutines.flow.Flow

@Dao
interface SubtaskDao {

    @Transaction
    @Query ("SELECT * FROM SUBTASK_TABLE")
    fun getAllSubtasks(): Flow<List<Subtask>>

    @Transaction
    @Query("SELECT * FROM SUBTASK_TABLE WHERE id=:id")
    suspend fun getSubTaskById(id: Long): Subtask

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubtask(subtask: Subtask)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSubtasks(subtasks: List<Subtask>)

    @Update
    suspend fun updateSubtask(subtask: Subtask)

    @Delete
    suspend fun deleteSubtask(subtask: Subtask)

    @Query("DELETE FROM subtask_table")
    suspend fun deleteAllSubtasks()
}