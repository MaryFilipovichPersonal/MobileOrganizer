package com.iit.secondcourse.mobileorganizer.data.db.utils

import androidx.room.Embedded
import androidx.room.Relation
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import com.iit.secondcourse.mobileorganizer.utils.TASK_ID

data class TaskWithSubtasks(
    @Embedded val task: Task,
    @Relation(
        parentColumn = "id",
        entityColumn = TASK_ID,
        entity = Subtask::class
    ) val subtasks: List<Subtask>
)
