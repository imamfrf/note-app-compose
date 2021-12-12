package com.imamfrf.noteappcompose

import androidx.compose.animation.animateColorAsState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.viewmodel.compose.viewModel
import com.imamfrf.noteappcompose.model.Note
import com.imamfrf.noteappcompose.view.NoteEditorView
import com.imamfrf.noteappcompose.view.NoteListView
import com.imamfrf.noteappcompose.viewmodel.AddEditNoteViewModel
import com.imamfrf.noteappcompose.viewmodel.NoteListViewModel
import com.imamfrf.noteappcompose.viewmodel.ViewModelFactory

@Composable
fun NoteListScreen(
    viewModelFactory: ViewModelFactory,
    onClickNote: (Long) -> Unit,
    onClickFab: () -> Unit
) {
    val viewModel: NoteListViewModel = viewModel(factory = viewModelFactory)
    val notes by viewModel.getAllNote().observeAsState()

    NoteListScaffold(
        title = "All Notes",
        fabIcon = Icons.Filled.Add,
        onClickFab = onClickFab
    ) {
        notes?.let {
            NoteListView(notes = it) { note ->
                onClickNote(note.id)
            }
        }
    }
}

@Composable
fun AddNoteScreen(
    viewModelFactory: ViewModelFactory,
    onNoteSaved: () -> Unit
) {
    val viewModel: AddEditNoteViewModel = viewModel(factory = viewModelFactory)

    EditNoteScaffold(
        title = "Add Note",
        backIcon = Icons.Filled.ArrowBack,
        onClickBack = onNoteSaved
    ) {
        NoteEditorView(
            note = Note(),
            isEdit = false,
            onSaveNote = {
                viewModel.addNote(it)
                onNoteSaved()
            }
        )
    }
}

@Composable
fun EditNoteScreen(
    viewModelFactory: ViewModelFactory,
    noteId: Long,
    onNoteEdited: () -> Unit
) {
    val viewModel: AddEditNoteViewModel = viewModel(factory = viewModelFactory)
    val selectedNote by viewModel.getNote(noteId).observeAsState()

    selectedNote?.let {
        EditNoteScaffold(
            title = it.title,
            isFavorite = it.isFavorite,
            backIcon = Icons.Filled.ArrowBack,
            onClickFavorite = { isFavorite ->
                viewModel.updateNote(it.copy(isFavorite = isFavorite))
            },
            onClickBack = onNoteEdited
        ) {
            NoteEditorView(
                note = it,
                isEdit = true,
                onDeleteNote = { note ->
                    viewModel.deleteNote(note)
                    onNoteEdited()
                },
                onSaveNote = { note ->
                    viewModel.updateNote(note)
                    onNoteEdited()
                }
            )
        }
    }
}

@Composable
fun EditNoteScaffold(
    title: String,
    backIcon: ImageVector? = null,
    isFavorite: Boolean? = null,
    onClickFavorite: (Boolean) -> Unit = {},
    onClickBack: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(title) },
                navigationIcon = {
                    backIcon?.let {
                        IconButton(onClick = { onClickBack() }) {
                            Icon(it, contentDescription = "Back")
                        }
                    }
                },
                actions = {
                    isFavorite?.let {
                        var favorited by remember { mutableStateOf(isFavorite) }

                        IconToggleButton(
                            checked = favorited,
                            onCheckedChange = {
                                onClickFavorite(it)
                                favorited = it
                            }) {
                            val tint by animateColorAsState(if (favorited) Color.Red else Color.Gray)
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Favorite",
                                tint = tint
                            )
                        }
                    }
                }
            )
        }
    ) {
        content()
    }
}

@Composable
fun NoteListScaffold(
    title: String,
    fabIcon: ImageVector? = null,
    onClickFab: () -> Unit = {},
    content: @Composable () -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text(title) })
        },

        floatingActionButton = {
            fabIcon?.let {
                FloatingActionButton(onClick = onClickFab) {
                    Icon(it, "Fab icon")
                }
            }
        }
    ) {
        content()
    }
}