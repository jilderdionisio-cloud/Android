package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.model.Usuario
import com.tecsup.ejercicio_1.viewmodel.UsuarioViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RegistroScreen(navController: NavController, viewModel: UsuarioViewModel) {
    var nombreCompleto by remember { mutableStateOf("") }
    var nombreUsuario by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var edad by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Crear cuenta", color = Color.White) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null, tint = Color.White)
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF112D4E))
            )
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            OutlinedTextField(value = nombreCompleto, onValueChange = { nombreCompleto = it }, label = { Text("Nombre completo") }, placeholder = { Text("Juan Pérez Vela") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = nombreUsuario, onValueChange = { nombreUsuario = it }, label = { Text("Usuario") }, placeholder = { Text("jperez") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = email, onValueChange = { email = it }, label = { Text("Email") }, placeholder = { Text("juan@mail.com") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = edad, onValueChange = { edad = it }, label = { Text("Edad") }, placeholder = { Text("25") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))
            OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Contraseña") }, placeholder = { Text("********") }, modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(12.dp))

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {
                    if (nombreCompleto.isEmpty() || nombreUsuario.isEmpty() || email.isEmpty() || edad.isEmpty() || password.isEmpty()) {
                        scope.launch { snackbarHostState.showSnackbar("Todos los campos son obligatorios") }
                        return@Button
                    }
                    if (!email.contains("@")) {
                        scope.launch { snackbarHostState.showSnackbar("Formato de email inválido") }
                        return@Button
                    }
                    
                    val fecha = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())
                    val nuevo = Usuario(
                        nombreUsuario = nombreUsuario,
                        password = password,
                        nombreCompleto = nombreCompleto,
                        email = email,
                        edad = edad.toIntOrNull() ?: 0,
                        fechaRegistro = fecha
                    )
                    viewModel.registrarUsuario(nuevo) {
                        navController.popBackStack()
                    }
                },
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF112D4E))
            ) {
                Text("Registrarme", fontSize = 16.sp)
            }
        }
    }
}
