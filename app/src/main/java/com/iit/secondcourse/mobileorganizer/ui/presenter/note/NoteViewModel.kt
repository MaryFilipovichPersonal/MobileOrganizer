package com.iit.secondcourse.mobileorganizer.ui.presenter.note

import android.util.Log
import androidx.lifecycle.*
import com.iit.secondcourse.mobileorganizer.data.entities.Note
import com.iit.secondcourse.mobileorganizer.data.repositories.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class NoteViewModel(private val repository: NoteRepository) : ViewModel() {

    val allNotes: LiveData<List<Note>> = repository.allNotes.asLiveData()

    private val _noteFromId = MutableLiveData<Note>()
    val noteFromId: LiveData<Note> get() = _noteFromId

    private val _isUpdated = MutableLiveData(false)
    val isUpdated: LiveData<Boolean> get() = _isUpdated

    fun insertNote(note: Note) = viewModelScope.launch {
        repository.insertNote(note)
    }

    fun updateNote(note: Note) = viewModelScope.launch {
        Log.d("NoteViewModel", "updateNote(note = $note)")
        repository.updateNote(note)
        setUpdatingProcess(true)
    }

    fun deleteNote(note: Note) = viewModelScope.launch {
        repository.deleteNote(note)
    }

    fun getNotes() = allNotes.value

    fun getNoteById(id: Long) {
        Log.d("NoteViewModel", "getNoteById(id = $id)")
        viewModelScope.launch {
            _noteFromId.value = repository.getNoteById(id)
        }
    }

    fun setUpdatingProcess(state: Boolean) {
        _isUpdated.value = state
    }

}