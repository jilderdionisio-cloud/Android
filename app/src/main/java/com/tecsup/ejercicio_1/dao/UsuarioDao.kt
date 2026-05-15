package com.tecsup.ejercicio_1.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.tecsup.ejercicio_1.model.Usuario

@Dao
interface UsuarioDao {

    @Insert
    suspend fun insertar(usuario: Usuario)

    @Query("""
        SELECT * FROM usuarios
        WHERE nombre_usuario = :usuario
        AND password = :password
        LIMIT 1
    """)
    suspend fun buscarPorCredenciales(
        usuario: String,
        password: String
    ): Usuario?

    @Query("""
        SELECT * FROM usuarios
        WHERE email = :email
        LIMIT 1
    """)
    suspend fun buscarPorEmail(email: String): Usuario?

    @Query("""
        SELECT * FROM usuarios
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun buscarPorId(id: Int): Usuario?
}
