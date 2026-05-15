package com.tecsup.ejercicio_1.repository

import com.tecsup.ejercicio_1.dao.RutinaDao
import com.tecsup.ejercicio_1.model.Rutina

class RutinaRepository(private val rutinaDao: RutinaDao) {
    suspend fun insertarRutina(rutina: Rutina) = rutinaDao.insertar(rutina)
    suspend fun obtenerRutinasPorUsuario(usuarioId: Int) = rutinaDao.listarPorUsuario(usuarioId)
    suspend fun eliminarRutina(rutina: Rutina) = rutinaDao.eliminar(rutina)
}
