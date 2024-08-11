package udb.edu.sv

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun NoteScreen(navController: NavHostController, noteLabel: String, weight: Double, nextRoute: String) {
    var note by remember { mutableStateOf(TextFieldValue()) }
    var showAlert by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }
    val weightPercentage = weight * 100

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ingrese $noteLabel (Peso: $weightPercentage%)")
        TextField(
            value = note,
            onValueChange = { note = it },
            label = { Text(noteLabel) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            val noteValue = note.text.toDoubleOrNull()
            when {
                note.text.isBlank() -> {
                    alertMessage = "No se admiten campos en blanco"
                    showAlert = true
                }
                noteValue == null || noteValue !in 0.0..10.0 -> {
                    alertMessage = "Valor ingresado no permitido. Debe ser entre 0 y 10"
                    showAlert = true
                }
                else -> {
                    StudentData.notes.add(noteValue * weight)
                    navController.navigate(nextRoute)
                }
            }
        }) {
            Text("Siguiente")
        }
    }

    if (showAlert) {
        AlertDialog(
            onDismissRequest = { showAlert = false },
            confirmButton = {
                Button(onClick = { showAlert = false }) {
                    Text("OK")
                }
            },
            title = { Text("Error") },
            text = { Text(alertMessage) }
        )
    }
}
