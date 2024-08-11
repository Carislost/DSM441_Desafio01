package udb.edu.sv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import udb.edu.sv.ui.theme.PromedioEstudianteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PromedioEstudianteTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "studentName") {
                    composable("studentName") { StudentNameScreen(navController) }
                    composable("note1") { NoteScreen(navController, "Nota 1", 0.15, "note2") }
                    composable("note2") { NoteScreen(navController, "Nota 2", 0.15, "note3") }
                    composable("note3") { NoteScreen(navController, "Nota 3", 0.20, "note4") }
                    composable("note4") { NoteScreen(navController, "Nota 4", 0.25, "note5") }
                    composable("note5") { NoteScreen(navController, "Nota 5", 0.25, "result") }
                    composable("result") { ResultScreen(navController) }
                }
            }
        }
    }
}
