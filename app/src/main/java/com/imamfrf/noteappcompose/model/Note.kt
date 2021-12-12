package com.imamfrf.noteappcompose.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var title: String = "",
    var content: String = "",
    var isFavorite: Boolean = false
)
