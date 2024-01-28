package com.example.examenapp


import android.app.Application
import androidx.room.Room
import com.example.examenapp.dbDao.BaseDatos

class App : Application() {
    private val db by lazy {
        try {
            Room.databaseBuilder(this, BaseDatos::class.java, "cuentas.db").build()
        } catch (e: Exception) {
            throw RuntimeException("Error al inicializar la base de datos", e)
        }
    }

    val cuentaDao by lazy { db.cuentaDao() }
}
