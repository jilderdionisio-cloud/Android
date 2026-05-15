package com.tecsup.ejercicio_1.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tecsup.ejercicio_1.dao.RutinaDao
import com.tecsup.ejercicio_1.model.Rutina
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RutinaViewModel(private val rutinaDao: RutinaDao) : ViewModel() {
    private val _rutinas = MutableStateFlow<List<Rutina>>(emptyList())
    val rutinas: StateFlow<List<Rutina>> = _rutinas

    fun cargarRutinas(usuarioId: Int) {
        viewModelScope.launch {
            _rutinas.value = rutinaDao.listarPorUsuario(usuarioId)
        }
    }

    fun agregarRutina(rutina: Rutina, onComplete: () -> Unit) {
        viewModelScope.launch {
            rutinaDao.insertar(rutina)
            onComplete()
        }
    }

    fun eliminarRutina(rutina: Rutina, usuarioId: Int) {
        viewModelScope.launch {
            rutinaDao.eliminar(rutina)
            cargarRutinas(usuarioId)
        }
    }

    suspend fun buscarPorId(id: Int) = rutinaDao.buscarPorId(id)

    fun actualizarRutina(rutina: Rutina, onComplete: () -> Unit) {
        viewModelScope.launch {
            rutinaDao.actualizar(rutina)
            onComplete()
        }
    }

    suspend fun obtenerEstadisticas(usuarioId: Int): Pair<Int, Double> {
        val count = rutinaDao.contarRutinas(usuarioId)
        val volume = rutinaDao.volumenTotal(usuarioId) ?: 0.0
        return Pair(count, volume)
    }
}
