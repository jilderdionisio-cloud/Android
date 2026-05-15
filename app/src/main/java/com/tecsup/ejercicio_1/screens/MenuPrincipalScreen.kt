package com.tecsup.ejercicio_1.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.tecsup.ejercicio_1.database.AppDatabase
import com.tecsup.ejercicio_1.model.Usuario
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MenuPrincipalScreen(navController: NavController, usuarioId: Int) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val context = LocalContext.current
    var usuario by remember { mutableStateOf<Usuario?>(null) }

    LaunchedEffect(usuarioId) {
        val db = AppDatabase.getInstance(context)
        usuario = db.usuarioDao().buscarPorId(usuarioId)
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Column(modifier = Modifier.padding(24.dp)) {
                    Box(
                        modifier = Modifier
                            .size(64.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFDBE2EF)),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = usuario?.nombreCompleto?.take(2)?.uppercase() ?: "??",
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF3F72AF)
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = usuario?.nombreCompleto ?: "Cargando...", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                    Text(text = usuario?.email ?: "", color = Color.Gray, fontSize = 14.sp)
                    Spacer(modifier = Modifier.height(24.dp))
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(16.dp))
                    DrawerItem(Icons.Default.Home, "Inicio", true) { scope.launch { drawerState.close() } }
                    DrawerItem(Icons.Default.Add, "Agregar rutina") { navController.navigate("agregar_rutina/$usuarioId") }
                    DrawerItem(Icons.AutoMirrored.Filled.List, "Mis rutinas") { navController.navigate("lista_rutinas/$usuarioId") }
                    DrawerItem(Icons.Default.Person, "Mi perfil") { navController.navigate("perfil/$usuarioId") }
                    DrawerItem(Icons.AutoMirrored.Filled.Logout, "Cerrar sesión") {
                        navController.navigate("login") { popUpTo("login") { inclusive = true } }
                    }
                }
            }
        }
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text("GymTracker Pro", color = Color.White) },
                    navigationIcon = {
                        IconButton(onClick = { scope.launch { drawerState.open() } }) {
                            Icon(Icons.Default.Menu, contentDescription = null, tint = Color.White)
                        }
                    },
                    actions = {
                        IconButton(onClick = { }) {
                            Icon(Icons.Default.Notifications, contentDescription = null, tint = Color.White)
                        }
                    },
                    colors = TopAppBarDefaults.topAppBarColors(containerColor = Color(0xFF112D4E))
                )
            }
        ) { padding ->
            Column(modifier = Modifier.padding(padding).padding(24.dp)) {
                Text(text = "Hola,", fontSize = 16.sp)
                Text(text = usuario?.nombreCompleto ?: "...", fontSize = 24.sp, fontWeight = FontWeight.Bold)
                
                Spacer(modifier = Modifier.height(32.dp))
                
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    item { MenuCard("Agregar rutina", Icons.Default.Add, Color(0xFFE3F2FD)) { navController.navigate("agregar_rutina/$usuarioId") } }
                    item { MenuCard("Mis rutinas", Icons.Default.List, Color(0xFFE8F5E9)) { navController.navigate("lista_rutinas/$usuarioId") } }
                    item { MenuCard("Mi perfil", Icons.Default.Person, Color(0xFFFFF3E0)) { navController.navigate("perfil/$usuarioId") } }
                    item { MenuCard("Cerrar sesión", Icons.Default.Logout, Color(0xFFFFEBEE)) {
                        navController.navigate("login") { popUpTo("login") { inclusive = true } }
                    } }
                }
            }
        }
    }
}

@Composable
fun DrawerItem(icon: ImageVector, label: String, selected: Boolean = false, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .clickable { onClick() },
        color = if (selected) Color(0xFFDBE2EF) else Color.Transparent
    ) {
        Row(
            modifier = Modifier.padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = null, tint = if (selected) Color(0xFF3F72AF) else Color.Gray)
            Spacer(modifier = Modifier.width(16.dp))
            Text(text = label, fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal)
        }
    }
}

@Composable
fun MenuCard(label: String, icon: ImageVector, color: Color, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f)
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = color)
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(icon, contentDescription = null, modifier = Modifier.size(32.dp), tint = Color.DarkGray)
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = label, fontWeight = FontWeight.Medium, color = Color.DarkGray, fontSize = 14.sp)
        }
    }
}
