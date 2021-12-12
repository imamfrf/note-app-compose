package com.imamfrf.noteappcompose.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imamfrf.noteappcompose.db.NoteDao
import com.imamfrf.noteappcompose.model.Note
import kotlinx.coroutines.launch

class AddEditNoteViewModel(private val noteDao: NoteDao) : ViewModel() {

    fun addNote(note: Note) {
        viewModelScope.launch {
            noteDao.insert(note)
        }
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            noteDao.update(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            noteDao.delete(note)
        }
    }

    fun getNote(id: Long): LiveData<Note> {
        return noteDao.get(id)
    }
}