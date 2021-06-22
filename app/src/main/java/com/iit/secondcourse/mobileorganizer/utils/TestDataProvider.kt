package com.iit.secondcourse.mobileorganizer.utils

import com.iit.secondcourse.mobileorganizer.data.db.utils.TaskWithSubtasks
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.data.entities.Subtask
import com.iit.secondcourse.mobileorganizer.data.entities.Task
import java.util.*

object TestDataProvider {

    fun getNotes(): List<Note> = arrayListOf(
        Note("Title 1", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 2", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 3", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 4", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 5", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 6", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 7", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 8", "some content", Calendar.getInstance(), Calendar.getInstance()),
        Note("Title 9", "some content", Calendar.getInstance(), Calendar.getInstance())
    )

    fun getTasks(): List<TaskWithSubtasks> = arrayListOf(
        TaskWithSubtasks(
            Task("Task 1", "some content", Calendar.getInstance(), Calendar.getInstance()),
            arrayListOf(
                Subtask(1L, "Subtask 1", "some content", true),
                Subtask(1L, "Subtask 2", "some content", true),
                Subtask(1L, "Subtask 3", "some content", false),
            )
        ),
        TaskWithSubtasks(
            Task("Task 2", "some content", Calendar.getInstance(), Calendar.getInstance()),
            arrayListOf(
                Subtask(2L, "Subtask 1", "some content", true),
                Subtask(2L, "Subtask 2", "some content", true),
                Subtask(2L, "Subtask 3", "some content", true),
                Subtask(2L, "Subtask 4", "some content", false),
                Subtask(2L, "Subtask 5", "some content", false)
            )
        ),
        TaskWithSubtasks(
            Task("Task 3", "some content", Calendar.getInstance(), Calendar.getInstance()),
            arrayListOf(
                Subtask(3L, "Subtask 1", "some content", false),
                Subtask(3L, "Subtask 2", "some content", true),
                Subtask(3L, "Subtask 3", "some content", false),
            )
        ),
        TaskWithSubtasks(
            Task("Task 4", "some content", Calendar.getInstance(), Calendar.getInstance()),
            arrayListOf(
                Subtask(4L, "Subtask 1", "some content", false),
                Subtask(4L, "Subtask 2", "some content", false),
                Subtask(4L, "Subtask 3", "some content", false),
                Subtask(4L, "Subtask 1", "some content", true),
                Subtask(4L, "Subtask 2", "some content", false),
                Subtask(4L, "Subtask 3", "some content", false)
            )
        ),
        TaskWithSubtasks(
            Task("Task 5", "some content", Calendar.getInstance(), Calendar.getInstance()),
            arrayListOf(
                Subtask(5L, "Subtask 1", "some content", true),
                Subtask(5L, "Subtask 2", "some content", true),
                Subtask(5L, "Subtask 3", "some content", true)
            )
        ),
        TaskWithSubtasks(
            Task("Task 6", "some content", Calendar.getInstance(), Calendar.getInstance()),
            arrayListOf(
                Subtask(6L, "Subtask 1", "some content", false),
                Subtask(6L, "Subtask 2", "some content", true)
            )
        )
    )

}