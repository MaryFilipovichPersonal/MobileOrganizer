package com.iit.secondcourse.mobileorganizer

import android.app.Application
import com.iit.secondcourse.mobileorganizer.data.db.MobileOrganizerDatabase
import com.iit.secondcourse.mobileorganizer.data.repositories.NoteRepository
import com.iit.secondcourse.mobileorganizer.data.repositories.TaskRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

class MobileOrganizerApplication : Application() {

    // No need to cancel this scope as it'll be torn down with the process
    val appScope = CoroutineScope(SupervisorJob())

    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
    val database by lazy { MobileOrganizerDatabase.getDatabase(this, appScope) }
    val noteRepository by lazy { NoteRepository(database.noteDao()) }
    val taskRepository by lazy { TaskRepository(database.taskDao(),database.subtaskDao()) }
}