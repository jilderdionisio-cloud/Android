package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.viewmodel.RutinaViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListaRutinasScreen(navController: NavController, viewModel: RutinaViewModel, usuarioId: Int) {
    val rutinas by viewModel.rutinas.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.cargarRutinas(usuarioId)
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mis Rutinas") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = { navController.navigate("agregar_rutina/$usuarioId") }) {
                Icon(Icons.Default.Add, contentDescription = "Agregar")
            }
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding).fillMaxSize()) {
            items(rutinas) { rutina ->
                Card(modifier = Modifier.padding(8.dp).fillMaxWidth()) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text("Ejercicio: ${rutina.ejercicio}", style = MaterialTheme.typography.titleLarge)
                        Text("Grupo: ${rutina.grupoMuscular}")
                        Text("Series: ${rutina.series} | Reps: ${rutina.repeticiones} | Peso: ${rutina.pesoKg}kg")
                        Text("Fecha: ${rutina.fecha}")
                        
                        Row {
                            IconButton(onClick = { navController.navigate("detalle_rutina/${rutina.id}") }) {
                                Icon(Icons.Default.Edit, contentDescription = "Editar")
                            }
                            IconButton(onClick = { viewModel.eliminarRutina(rutina, usuarioId) }) {
                                Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                            }
                        }
                    }
                }
            }
        }
    }
}
