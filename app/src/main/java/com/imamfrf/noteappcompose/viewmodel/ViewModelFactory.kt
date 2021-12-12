package com.imamfrf.noteappcompose.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.imamfrf.noteappcompose.db.NoteDao

class ViewModelFactory(val noteDao: NoteDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(AddEditNoteViewModel::class.java) -> AddEditNoteViewModel(noteDao) as T

            modelClass.isAssignableFrom(NoteListViewModel::class.java) -> NoteListViewModel(noteDao) as T

            else -> throw IllegalArgumentException("Cannot find a viewmodel")
        }
    }
}