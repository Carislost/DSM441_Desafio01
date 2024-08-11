package udb.edu.sv

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun ResultScreen(navController: NavHostController) {
    val average = StudentData.notes.sum()
    val passed = average >= 5.0
    val resultText = if (passed) "Aprobado" else "Reprobado"

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Nombre del estudiante: ${StudentData.studentName}", fontSize = 20.sp)
        Text("Promedio final: %.2f".format(average), fontSize = 20.sp)
        Text("Resultado: $resultText", fontSize = 20.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = {
            StudentData.reset()
            navController.navigate("studentName") {
                popUpTo("studentName") { inclusive = true }
            }
        }) {
            Text("Reiniciar")
        }
    }
}
