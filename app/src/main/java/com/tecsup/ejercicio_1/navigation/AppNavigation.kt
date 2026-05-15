package com.tecsup.ejercicio_1.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tecsup.ejercicio_1.database.AppDatabase
import com.tecsup.ejercicio_1.screens.*
import com.tecsup.ejercicio_1.viewmodel.RutinaViewModel
import com.tecsup.ejercicio_1.viewmodel.UsuarioViewModel

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val db = AppDatabase.getInstance(context)
    
    // Instanciamos los ViewModels directamente (forma simple para principiantes)
    val usuarioViewModel = UsuarioViewModel(db.usuarioDao())
    val rutinaViewModel = RutinaViewModel(db.rutinaDao())

    NavHost(navController = navController, startDestination = "login") {
        composable("login") { 
            LoginScreen(navController, usuarioViewModel) 
        }
        composable("registro") { 
            RegistroScreen(navController, usuarioViewModel) 
        }
        composable(
            "menu_principal/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
            MenuPrincipalScreen(navController, usuarioId)
        }
        composable(
            "agregar_rutina/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
            AgregarRutinaScreen(navController, rutinaViewModel, usuarioId)
        }
        composable(
            "lista_rutinas/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
            ListaRutinasScreen(navController, rutinaViewModel, usuarioId)
        }
        composable(
            "detalle_rutina/{rutinaId}",
            arguments = listOf(navArgument("rutinaId") { type = NavType.IntType })
        ) { backStackEntry ->
            val rutinaId = backStackEntry.arguments?.getInt("rutinaId") ?: 0
            DetalleRutinaScreen(navController, rutinaViewModel, rutinaId)
        }
        composable(
            "perfil/{usuarioId}",
            arguments = listOf(navArgument("usuarioId") { type = NavType.IntType })
        ) { backStackEntry ->
            val usuarioId = backStackEntry.arguments?.getInt("usuarioId") ?: 0
            PerfilUsuarioScreen(navController, usuarioViewModel, rutinaViewModel, usuarioId)
        }
    }
}
