package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipalScreen(navController: NavController, usuarioId: Int) {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("GymTracker Pro - Menú") })
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Button(
                onClick = { navController.navigate("agregar_rutina/$usuarioId") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Agregar Rutina")
            }
            Button(
                onClick = { navController.navigate("lista_rutinas/$usuarioId") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mis Rutinas")
            }
            Button(
                onClick = { navController.navigate("perfil/$usuarioId") },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mi Perfil")
            }
        }
    }
}
