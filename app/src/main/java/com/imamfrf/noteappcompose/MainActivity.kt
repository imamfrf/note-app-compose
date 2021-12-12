package com.imamfrf.noteappcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.imamfrf.noteappcompose.db.NoteDatabase
import com.imamfrf.noteappcompose.model.Note
import com.imamfrf.noteappcompose.navigation.MainNavigation
import com.imamfrf.noteappcompose.ui.theme.NoteAppComposeTheme
import com.imamfrf.noteappcompose.view.NoteItem
import com.imamfrf.noteappcompose.viewmodel.ViewModelFactory

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val noteDao = NoteDatabase.getInstance(this).noteDao
        val viewModelFactory = ViewModelFactory(noteDao)

        setContent {
            NoteAppComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    MainNavigation(viewModelFactory = viewModelFactory)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NoteAppComposeTheme {
        NoteItem(
            note = Note(title = "Note", content = "Note content", isFavorite = true),
            onClickNote = {})
    }
}