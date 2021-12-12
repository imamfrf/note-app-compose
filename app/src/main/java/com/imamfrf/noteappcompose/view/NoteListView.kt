package com.imamfrf.noteappcompose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.imamfrf.noteappcompose.R
import com.imamfrf.noteappcompose.model.Note

@Composable
fun NoteListView(notes: List<Note>, onClickNote: (Note) -> Unit) {
    LazyColumn {
        items(notes.size) {
            val note = notes[it]
            NoteItem(note, onClickNote)
        }
    }
}

@Composable
fun NoteItem(note: Note, onClickNote: (Note) -> Unit) {
    Card(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp),
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable { onClickNote(note) }
            .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(7f)
            ) {
                Text(
                    text = note.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )

                Text(
                    text = note.content,
                    fontSize = 12.sp,
                    maxLines = 3
                )

            }

            if (note.isFavorite) {
                Image(
                    painter = painterResource(id = R.drawable.ic_baseline_favorite_24),
                    contentDescription = "Favorite Icon",
                    modifier = Modifier
                        .weight(1f)
                        .align(Alignment.CenterVertically)
                )
            }

        }
    }

}
