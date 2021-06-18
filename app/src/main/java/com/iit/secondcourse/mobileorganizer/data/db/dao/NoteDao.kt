package com.iit.secondcourse.mobileorganizer.data.db.dao

import androidx.room.*
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.utils.NOTE_DATE_UPDATE
import com.iit.secondcourse.mobileorganizer.utils.NOTE_TABLE_NAME
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {
    @Query("SELECT * FROM $NOTE_TABLE_NAME ORDER BY $NOTE_DATE_UPDATE DESC")
    fun getNotesInUpdateDateOrder(): Flow<List<Note>>

    @Query("SELECT * FROM $NOTE_TABLE_NAME WHERE id=:id")
    fun getNoteById(id: Long): Note

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Query("DELETE FROM note_table")
    suspend fun deleteAllNotes()

    //for test data setting
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNotesList(notes: List<Note>)
}