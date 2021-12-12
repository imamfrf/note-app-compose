package com.imamfrf.noteappcompose.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.imamfrf.noteappcompose.model.Note

@Dao
interface NoteDao {
    @Insert
    suspend fun insert(note: Note)

    @Update
    suspend fun update(note: Note)

    @Delete
    suspend fun delete(note: Note)

    @Query("SELECT * FROM note WHERE id = :key")
    fun get(key: Long): LiveData<Note>

    @Query("SELECT * FROM note ORDER BY id DESC")
    fun getAll(): LiveData<List<Note>>
}