package com.iit.secondcourse.mobileorganizer.ui.presenter.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.iit.secondcourse.mobileorganizer.data.repositories.NoteRepository
import java.lang.IllegalArgumentException

class NoteViewModelFactory (private val repository: NoteRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NoteViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteViewModel(repository) as T
        }
        throw  IllegalArgumentException("Unknown ViewModel class")
    }
}