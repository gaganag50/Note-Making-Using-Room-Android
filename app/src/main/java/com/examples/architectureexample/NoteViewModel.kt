package com.examples.architectureexample

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData


class NoteViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: NoteRepository
    val allNotes: LiveData<List<Note>>

    fun insert(note: Note?) {
        repository.insert(note)
    }

    fun update(note: Note?) {
        repository.update(note)
    }

    fun delete(note: Note?) {
        repository.delete(note)
    }

    fun deleteAllNotes() {
        repository.deleteAllNotes()
    }

    init {
        repository = NoteRepository(application)
        allNotes = repository.allNotes
    }
}