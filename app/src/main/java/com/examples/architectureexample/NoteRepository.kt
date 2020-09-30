package com.examples.architectureexample

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData


class NoteRepository(application: Application) {
    private val noteDao: NoteDao
    val allNotes: LiveData<List<Note>>

    fun insert(note: Note?) {
        InsertNoteAsyncTask(noteDao).execute(note)
    }

    fun update(note: Note?) {
        UpdateNoteAsyncTask(noteDao).execute(note)
    }

    fun delete(note: Note?) {
        DeleteNoteAsyncTask(noteDao).execute(note)
    }

    fun deleteAllNotes() {
        DeleteAllNotesAsyncTask(noteDao).execute()
    }

    private class InsertNoteAsyncTask constructor(private val noteDao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let {
                noteDao.insert(it)
            }
            return null
        }


    }

    private class UpdateNoteAsyncTask constructor(private val noteDao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.update(it) }
            return null
        }
    }

    private class DeleteNoteAsyncTask constructor(private val noteDao: NoteDao) :
        AsyncTask<Note?, Void?, Void?>() {
        override fun doInBackground(vararg notes: Note?): Void? {
            notes[0]?.let { noteDao.delete(it) }
            return null
        }
    }

    private class DeleteAllNotesAsyncTask constructor(private val noteDao: NoteDao) :
        AsyncTask<Void?, Void?, Void?>() {
        override fun doInBackground(vararg voids: Void?): Void? {
            noteDao.deleteAllNotes()
            return null
        }
    }

    init {
        val database = NoteDatabase.getInstance(application)
        noteDao = database.noteDao()
        allNotes = noteDao.allNotes
    }
}