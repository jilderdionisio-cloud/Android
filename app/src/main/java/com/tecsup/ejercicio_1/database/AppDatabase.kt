package com.tecsup.ejercicio_1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.tecsup.ejercicio_1.dao.RutinaDao
import com.tecsup.ejercicio_1.dao.UsuarioDao
import com.tecsup.ejercicio_1.model.Rutina
import com.tecsup.ejercicio_1.model.Usuario

@Database(entities = [Usuario::class, Rutina::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun rutinaDao(): RutinaDao
}
