package udb.edu.sv

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun StudentNameScreen(navController: NavHostController) {
    var studentName by remember { mutableStateOf(TextFieldValue()) }
    var showAlert by remember { mutableStateOf(false) }
    var alertMessage by remember { mutableStateOf("") }

    fun isValidName(name: String): Boolean {
        return name.isNotBlank() && name.all { it.isLetter() || it.isWhitespace() }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Ingrese el nombre del estudiante")
        TextField(
            value = studentName,
            onValueChange = { studentName = it },
            label = { Text("Nombre del estudiante") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            when {
                studentName.text.isBlank() -> {
                    alertMessage = "No se admiten campos en blanco"
                    showAlert = true
                }
                !isValidName(studentName.text) -> {
                    alertMessage = "No se admiten números ni caracteres especiales"
                    showAlert = true
                }
                else -> {
                    StudentData.studentName = studentName.text
                    navController.navigate("note1")
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
