package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.model.Usuario
import com.tecsup.ejercicio_1.viewmodel.UsuarioViewModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun RegistroScreen(navController: NavController, viewModel: UsuarioViewModel) {
    var nombreUsuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var nombreCompleto by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var errorMsg by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Registro de Usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = nombreUsuario, onValueChange = { nombreUsuario = it }, label = { Text("Nombre de Usuario") }, modifier = Modifier.fillMaxWidth())
        TextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, modifier = Modifier.fillMaxWidth())
        TextField(value = nombreCompleto, onValueChange = { nombreCompleto = it }, label = { Text("Nombre Completo") }, modifier = Modifier.fillMaxWidth())
        TextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, modifier = Modifier.fillMaxWidth())
        TextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") }, modifier = Modifier.fillMaxWidth())

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                if (!email.contains("@")) {
                    errorMsg = "Email inválido"
                } else {
                    val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                    val nuevoUsuario = Usuario(
                        nombreUsuario = nombreUsuario,
                        password = password,
                        nombreCompleto = nombreCompleto,
                        email = email,
                        edad = edad.toIntOrNull() ?: 0,
                        fechaRegistro = fecha
                    )
                    viewModel.registrarUsuario(nuevoUsuario) {
                        navController.popBackStack()
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        if (errorMsg.isNotEmpty()) {
            Text(errorMsg, color = MaterialTheme.colorScheme.error)
        }
    }
}
