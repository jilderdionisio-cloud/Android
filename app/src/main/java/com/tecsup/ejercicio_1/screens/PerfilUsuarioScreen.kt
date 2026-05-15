package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.model.Usuario
import com.tecsup.ejercicio_1.viewmodel.RutinaViewModel
import com.tecsup.ejercicio_1.viewmodel.UsuarioViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PerfilUsuarioScreen(
    navController: NavController,
    uViewModel: UsuarioViewModel,
    rViewModel: RutinaViewModel,
    usuarioId: Int
) {
    var usuario by remember { mutableStateOf<Usuario?>(null) }
    var totalRutinas by remember { mutableIntStateOf(0) }
    var volumenTotal by remember { mutableDoubleStateOf(0.0) }

    LaunchedEffect(usuarioId) {
        usuario = uViewModel.buscarPorId(usuarioId)
        val stats = rViewModel.obtenerEstadisticas(usuarioId)
        totalRutinas = stats.first
        volumenTotal = stats.second
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text("Mi Perfil") }) }
    ) { padding ->
        Column(modifier = Modifier.padding(padding).padding(16.dp)) {
            usuario?.let {
                Text("Nombre Completo: ${it.nombreCompleto}")
                Text("Usuario: ${it.nombreUsuario}")
                Text("Email: ${it.email}")
                Text("Edad: ${it.edad}")
                Text("Fecha Registro: ${it.fechaRegistro}")
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Text("Estadísticas", style = MaterialTheme.typography.titleLarge)
                Text("Total de Rutinas: $totalRutinas")
                Text("Volumen Total: $volumenTotal kg")
                
                Spacer(modifier = Modifier.height(24.dp))
                
                Button(
                    onClick = {
                        navController.navigate("login") {
                            popUpTo("login") { inclusive = true }
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Cerrar Sesión")
                }
            }
        }
    }
}
