package com.tecsup.ejercicio_1.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tecsup.ejercicio_1.screens.*

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "login") {
        composable("login") { LoginScreen() }
        composable("registro") { RegistroScreen() }
        composable("menu_principal") { MenuPrincipalScreen() }
        composable("agregar_rutina") { AgregarRutinaScreen() }
        composable("lista_rutinas") { ListaRutinasScreen() }
        composable("detalle_rutina") { DetalleRutinaScreen() }
        composable("perfil") { PerfilUsuarioScreen() }
    }
}
