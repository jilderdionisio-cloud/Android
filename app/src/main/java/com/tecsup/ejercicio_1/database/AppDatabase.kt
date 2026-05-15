package com.tecsup.ejercicio_1.database

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.tecsup.ejercicio_1.dao.RutinaDao
import com.tecsup.ejercicio_1.dao.UsuarioDao
import com.tecsup.ejercicio_1.model.Rutina
import com.tecsup.ejercicio_1.model.Usuario

@Database(entities = [Usuario::class, Rutina::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun usuarioDao(): UsuarioDao
    abstract fun rutinaDao(): RutinaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: android.content.Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "gym_tracker_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
