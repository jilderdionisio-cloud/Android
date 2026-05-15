package com.tecsup.ejercicio_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.ejercicio_1.model.Rutina
import com.tecsup.ejercicio_1.repository.RutinaRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RutinaViewModel(private val repository: RutinaRepository) : ViewModel() {
    private val _rutinas = MutableStateFlow<List<Rutina>>(emptyList())
    val rutinas: StateFlow<List<Rutina>> = _rutinas

    fun cargarRutinas(usuarioId: Int) {
        viewModelScope.launch {
            _rutinas.value = repository.obtenerRutinasPorUsuario(usuarioId)
        }
    }

    fun agregarRutina(rutina: Rutina) {
        viewModelScope.launch {
            repository.insertarRutina(rutina)
            cargarRutinas(rutina.usuarioId)
        }
    }
}
