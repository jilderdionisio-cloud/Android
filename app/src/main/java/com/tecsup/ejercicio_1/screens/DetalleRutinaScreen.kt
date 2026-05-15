package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.model.Rutina
import com.tecsup.ejercicio_1.viewmodel.RutinaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleRutinaScreen(navController: NavController, viewModel: RutinaViewModel, rutinaId: Int) {
    var rutina by remember { mutableStateOf<Rutina?>(null) }
    
    var ejercicio by remember { mutableStateOf("") }
    var grupoMuscular by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var repeticiones by remember { mutableStateOf("") }
    var pesoKg by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    LaunchedEffect(rutinaId) {
        val r = viewModel.buscarPorId(rutinaId)
        if (r != null) {
            rutina = r
            ejercicio = r.ejercicio
            grupoMuscular = r.grupoMuscular
            series = r.series.toString()
            repeticiones = r.repeticiones.toString()
            pesoKg = r.pesoKg.toString()
            fecha = r.fecha
        }
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Editar Rutina") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            TextField(value = ejercicio, onValueChange = { ejercicio = it }, label = { Text("Ejercicio") }, modifier = Modifier.fillMaxWidth())
            TextField(value = grupoMuscular, onValueChange = { grupoMuscular = it }, label = { Text("Grupo Muscular") }, modifier = Modifier.fillMaxWidth())
            TextField(value = series, onValueChange = { series = it }, label = { Text("Series") }, modifier = Modifier.fillMaxWidth())
            TextField(value = repeticiones, onValueChange = { repeticiones = it }, label = { Text("Repeticiones") }, modifier = Modifier.fillMaxWidth())
            TextField(value = pesoKg, onValueChange = { pesoKg = it }, label = { Text("Peso (kg)") }, modifier = Modifier.fillMaxWidth())
            TextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") }, modifier = Modifier.fillMaxWidth())

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    rutina?.let {
                        val actualizada = it.copy(
                            ejercicio = ejercicio,
                            grupoMuscular = grupoMuscular,
                            series = series.toIntOrNull() ?: 0,
                            repeticiones = repeticiones.toIntOrNull() ?: 0,
                            pesoKg = pesoKg.toDoubleOrNull() ?: 0.0,
                            fecha = fecha
                        )
                        viewModel.actualizarRutina(actualizada) {
                            navController.popBackStack()
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Actualizar")
            }
        }
    }
}
