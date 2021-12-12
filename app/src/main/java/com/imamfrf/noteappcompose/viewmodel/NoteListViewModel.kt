package com.imamfrf.noteappcompose.viewmodel

import androidx.lifecycle.ViewModel
import com.imamfrf.noteappcompose.db.NoteDao

class NoteListViewModel(private val noteDao: NoteDao) : ViewModel() {
    fun getAllNote() = noteDao.getAll()
}