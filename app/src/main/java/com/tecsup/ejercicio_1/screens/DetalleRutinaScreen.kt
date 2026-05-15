package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.model.Rutina
import com.tecsup.ejercicio_1.viewmodel.RutinaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetalleRutinaScreen(navController: NavController, viewModel: RutinaViewModel, rutinaId: Int) {
    var rutinaOriginal by remember { mutableStateOf<Rutina?>(null) }
    
    var ejercicio by remember { mutableStateOf("") }
    var grupoMuscular by remember { mutableStateOf("") }
    var series by remember { mutableStateOf("") }
    var repeticiones by remember { mutableStateOf("") }
    var pesoKg by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

    LaunchedEffect(rutinaId) {
        val r = viewModel.buscarPorId(rutinaId)
        if (r != null) {
            rutinaOriginal = r
            ejercicio = r.ejercicio
            grupoMuscular = r.grupoMuscular
            series = r.series.toString()
            repeticiones = r.repeticiones.toString()
            pesoKg = r.pesoKg.toString()
            fecha = r.fecha
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Editar rutina #$rutinaId", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                actions = {
                    IconButton(onClick = {
                        rutinaOriginal?.let {
                            viewModel.eliminarRutina(it, it.usuarioId)
                            navController.popBackStack()
                        }
                    }) {
                        Icon(Icons.Default.Delete, contentDescription = null, tint = Color.White)
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
            // Info box
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(8.dp))
                    .background(Color(0xFFE3F2FD))
                    .padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(Icons.Default.Info, contentDescription = null, tint = Color(0xFF3F72AF), modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Modificando registro existente", fontSize = 14.sp, color = Color(0xFF3F72AF))
            }

            OutlinedTextField(value = ejercicio, onValueChange = { ejercicio = it }, label = { Text("Ejercicio") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = grupoMuscular, onValueChange = { grupoMuscular = it }, label = { Text("Grupo muscular") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                OutlinedTextField(value = series, onValueChange = { series = it }, label = { Text("Series") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
                OutlinedTextField(value = repeticiones, onValueChange = { repeticiones = it }, label = { Text("Repeticiones") }, modifier = Modifier.weight(1f), shape = RoundedCornerShape(12.dp))
            }
            
            OutlinedTextField(value = pesoKg, onValueChange = { pesoKg = it }, label = { Text("Peso (kg)") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp), trailingIcon = { Icon(Icons.Default.CalendarMonth, contentDescription = null) })

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
                    rutinaOriginal?.let {
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
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF1B5E20)) // Dark green
            ) {
                Text("Actualizar cambios", fontSize = 16.sp)
            }
        }
    }
}
