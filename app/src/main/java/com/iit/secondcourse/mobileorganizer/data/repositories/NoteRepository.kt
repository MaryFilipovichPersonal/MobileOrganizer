package com.iit.secondcourse.mobileorganizer.data.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import com.iit.secondcourse.mobileorganizer.data.db.dao.NoteDao
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import kotlinx.coroutines.flow.Flow

// Declares the DAO as a private property in the constructor. Pass in the DAO
// instead of the whole database, because you only need access to the DAO
class NoteRepository(private val noteDao: NoteDao) {

    // Room executes all queries on a separate thread.
    // Observed Flow will notify the observer when the data has changed.
    val allNotes: Flow<List<Note>> = noteDao.getNotesInUpdateDateOrder()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun getNoteById(id: Long) = noteDao.getNoteById(id)

    // By default Room runs suspend queries off the main thread, therefore, we don't need to
    // implement anything else to ensure we're not doing long running database work
    // off the main thread.
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insertNote(note: Note){
        noteDao.insertNote(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun deleteNote(note: Note){
        noteDao.deleteNote(note)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun updateNote(note: Note){
        Log.d("Repository", "updateNote(note = $note)")
        noteDao.updateNote(note)
    }

}