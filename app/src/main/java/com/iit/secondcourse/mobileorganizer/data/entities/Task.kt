package com.iit.secondcourse.mobileorganizer.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.iit.secondcourse.mobileorganizer.utils.*
import java.util.*

@Entity(tableName = TASK_TABLE_NAME)
data class Task(
    @ColumnInfo(name = TASK_TITLE) val title: String,
    @ColumnInfo(name = TASK_DESCRIPTION) val description: String,
    @ColumnInfo(name = TASK_DATE_START) val dateStart: Calendar,
    @ColumnInfo(name = TASK_DATE_DEADLINE) val dateDeadline: Calendar,
    @ColumnInfo(name = TASK_COMPLETED) val isCompleted: Boolean
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0L
}
