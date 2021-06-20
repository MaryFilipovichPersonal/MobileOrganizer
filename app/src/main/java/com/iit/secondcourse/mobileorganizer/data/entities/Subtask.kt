package com.iit.secondcourse.mobileorganizer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iit.secondcourse.mobileorganizer.utils.*
import java.util.*

@Entity(tableName = SUBTASK_TABLE_NAME)
data class Subtask(
    @ColumnInfo(name = TASK_ID) val taskId: Long,
    @ColumnInfo(name = SUBTASK_TITLE) val title: String,
    @ColumnInfo(name = SUBTASK_DESCRIPTION) val description: String,
    @ColumnInfo(name = SUBTASK_COMPLETED) val isCompleted: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
