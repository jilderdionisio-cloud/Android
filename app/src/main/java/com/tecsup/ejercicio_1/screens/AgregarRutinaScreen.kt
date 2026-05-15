package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Save
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.model.Rutina
import com.tecsup.ejercicio_1.viewmodel.RutinaViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AgregarRutinaScreen(navController: NavController, viewModel: RutinaViewModel, usuarioId: Int) {
    var ejercicio by remember { mutableStateOf("") }
    var grupoMuscular by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var repeticiones by remember { mutableStateOf("") }
    var pesoKg by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf(SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Nueva rutina", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        val nuevaRutina = Rutina(
                            usuarioId = usuarioId,
                            ejercicio = ejercicio,
                            grupoMuscular = grupoMuscular,
                            series = series.toIntOrNull() ?: 0,
                            repeticiones = repeticiones.toIntOrNull() ?: 0,
                            pesoKg = pesoKg.toDoubleOrNull() ?: 0.0,
                            fecha = fecha
                        )
                        viewModel.agregarRutina(nuevaRutina) {
                            navController.popBackStack()
                        }
                    }) {
                        Icon(Icons.Default.Save, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF112D4E))
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            OutlinedTextField(value = ejercicio, onValueChange = { ejercicio = it }, label = { Text("Ejercicio") }, placeholder = { Text("Press banca") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = grupoMuscular, onValueChange = { grupoMuscular = it }, label = { Text("Grupo muscular") }, placeholder = { Text("Pecho") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(value = series, onValueChange = { series = it }, label = { Text("Series") }, placeholder = { Text("4") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = repeticiones, onValueChange = { repeticiones = it }, label = { Text("Repeticiones") }, placeholder = { Text("12") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
            }
            
            OutlinedTextField(value = pesoKg, onValueChange = { pesoKg = it }, label = { Text("Peso (kg)") }, placeholder = { Text("60.5") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") }, placeholder = { Text("12/05/2026") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), trailingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) })

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    val nuevaRutina = Rutina(
                        usuarioId = usuarioId,
                        ejercicio = ejercicio,
                        grupoMuscular = grupoMuscular,
                        series = series.toIntOrNull() ?: 0,
                        repeticiones = repeticiones.toIntOrNull() ?: 0,
                        pesoKg = pesoKg.toDoubleOrNull() ?: 0.0,
                        fecha = fecha
                    )
                    viewModel.agregarRutina(nuevaRutina) {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF112D4E))
            ) {
                Text("Guardar rutina")
            }
        }
    }
}
