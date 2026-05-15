package com.tecsup.ejercicio_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.ejercicio_1.model.Usuario
import com.tecsup.ejercicio_1.repository.UsuarioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class UsuarioViewModel(private val repository: UsuarioRepository) : ViewModel() {
    private val _usuarioLogueado = MutableStateFlow<Usuario?>(null)
    val usuarioLogueado: StateFlow<Usuario?> = _usuarioLogueado

    fun registrarUsuario(usuario: Usuario) {
        viewModelScope.launch {
            repository.insertarUsuario(usuario)
        }
    }

    fun login(nombreUsuario: String, password: String) {
        viewModelScope.launch {
            val usuario = repository.login(nombreUsuario, password)
            _usuarioLogueado.value = usuario
        }
    }
}
