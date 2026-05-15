package com.tecsup.ejercicio_1.repository

import com.tecsup.ejercicio_1.dao.UsuarioDao
import com.tecsup.ejercicio_1.model.Usuario

class UsuarioRepository(private val usuarioDao: UsuarioDao) {
    suspend fun insertarUsuario(usuario: Usuario) = usuarioDao.insertar(usuario)
    suspend fun login(usuario: String, password: String) = usuarioDao.login(usuario, password)
    suspend fun buscarPorEmail(email: String) = usuarioDao.buscarPorEmail(email)
}
