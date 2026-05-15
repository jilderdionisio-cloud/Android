package com.tecsup.ejercicio_1.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room. Update
import androidx.room. Delete
import com.tecsup.ejercicio_1.model.Rutina

@Dao
interface RutinaDao {

    @Insert
    suspend fun insertar(rutina: Rutina)

    @Update
    suspend fun actualizar(rutina: Rutina)

    @Delete
    suspend fun eliminar(rutina: Rutina)

    @Query("""
        SELECT * FROM rutinas
        WHERE usuario_id = :usuarioId
    """)
    suspend fun listarPorUsuario(
        usuarioId: Int
    ): List<Rutina>

    @Query("""
        SELECT * FROM rutinas
        WHERE id = :id
        LIMIT 1
    """)
    suspend fun buscarPorId(id: Int): Rutina?

    @Query("""
        SELECT COUNT(*) FROM rutinas
        WHERE usuario_id = :id
    """)
    suspend fun contarRutinas(id: Int): Int

    @Query("""
        SELECT SUM(peso_kg * series * repeticiones) FROM rutinas
        WHERE usuario_id = :id
    """)
    suspend fun volumenTotal(id: Int): Double?
}
