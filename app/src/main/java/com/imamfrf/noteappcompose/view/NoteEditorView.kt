package com.imamfrf.noteappcompose.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.imamfrf.noteappcompose.model.Note

@Composable
fun NoteEditorView(
    note: Note,
    isEdit: Boolean = false,
    onSaveNote: (Note) -> Unit = {},
    onDeleteNote: (Note) -> Unit = {}
) {
    var title by rememberSaveable(note.title) { mutableStateOf(note.title) }
    var content by rememberSaveable(note.content) { mutableStateOf(note.content) }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        OutlinedTextField(
            value = title,
            modifier = Modifier
                .fillMaxWidth(),
            placeholder = { Text(text = "Title") },
            label = { Text(text = "Title") },
            onValueChange = { title = it }
        )

        Spacer(modifier = Modifier.size(8.dp))

        OutlinedTextField(
            value = content,
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp),
            placeholder = { Text(text = "Content") },
            label = { Text(text = "Content") },
            onValueChange = { content = it })

        Spacer(modifier = Modifier.size(24.dp))

        Button(
            onClick = { onSaveNote(note.copy(title = title, content = content)) },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(text = "Save")
        }

        if (isEdit) {
            Spacer(modifier = Modifier.size(16.dp))

            Button(
                onClick = { onDeleteNote(note) },
                modifier = Modifier
                    .fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
            ) {
                Text(
                    text = "Delete",
                    color = Color.White
                )
            }
        }
    }
}