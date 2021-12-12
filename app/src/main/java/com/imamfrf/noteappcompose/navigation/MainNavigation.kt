package com.imamfrf.noteappcompose.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.imamfrf.noteappcompose.AddNoteScreen
import com.imamfrf.noteappcompose.EditNoteScreen
import com.imamfrf.noteappcompose.NoteListScreen
import com.imamfrf.noteappcompose.viewmodel.ViewModelFactory

@Composable
fun MainNavigation(viewModelFactory: ViewModelFactory) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "/notes") {
        composable("/notes") {
            NoteListScreen(
                viewModelFactory = viewModelFactory,
                onClickNote = { navController.navigate("/notes/$it") },
                onClickFab = { navController.navigate("/addNote") }
            )
        }

        composable("/addNote") {
            AddNoteScreen(
                viewModelFactory = viewModelFactory
            ) { navController.popBackStack() }
        }

        composable(
            "/notes/{noteId}",
            arguments = listOf(navArgument("noteId") {
                type = NavType.LongType
            })
        ) {
            it.arguments?.let { arg ->
                val noteId = arg.get("noteId") as Long
                EditNoteScreen(
                    viewModelFactory = viewModelFactory,
                    noteId = noteId
                ) {
                    navController.popBackStack()
                }
            }
        }
    }
}