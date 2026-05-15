package com.tecsup.ejercicio_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.ejercicio_1.dao.UsuarioDao
import com.tecsup.ejercicio_1.model.Usuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(private val usuarioDao: UsuarioDao) : ViewModel() {
    private val _usuarioLogueado = MutableStateFlow<Usuario?>(null)
    val usuarioLogueado: StateFlow<Usuario?> = _usuarioLogueado

    fun registrarUsuario(usuario: Usuario, onSuccess: () -> Unit) {
        viewModelScope.launch {
            usuarioDao.insertar(usuario)
            onSuccess()
        }
    }

    fun login(nombreUsuario: String, password: String, onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            val usuario = usuarioDao.login(nombreUsuario, password)
            _usuarioLogueado.value = usuario
            onResult(usuario != null)
        }
    }

    suspend fun buscarPorId(id: Int): Usuario? {
        return usuarioDao.buscarPorId(id)
    }

    suspend fun existeUsuarioOEmail(nombreUsuario: String, email: String): Boolean {
        val u = usuarioDao.login(nombreUsuario, "") // Simplificado para este ejemplo
        val e = usuarioDao.buscarPorEmail(email)
        return e != null
    }
}
