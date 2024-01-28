

package com.example.examenapp.dbDao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.PrimaryKey
import androidx.room.Query
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import androidx.room.Update
import java.time.LocalDate


//Codigo para crear tablas
@Entity
data class Cuentas(
    @PrimaryKey(autoGenerate = true) var id:Long? = null,
    var fechaCuenta: LocalDate,
    var cuentaValor: String,
    var cuentaTipo: String
)


// Convertidores para LocalDate
class DbConverters {
    @TypeConverter
    fun fromTimestamp(value: Long?): LocalDate? {
        return value?.let { LocalDate.ofEpochDay(it) }
    }
    @TypeConverter
    fun dateToTimestamp(date: LocalDate): Long? {
        return date.toEpochDay()
    }
}

@Database(entities = [Cuentas::class], version = 1)
@TypeConverters(DbConverters::class)
abstract class BaseDatos : RoomDatabase() {
    abstract fun cuentaDao(): CuentaDao
}


//Codigo para hacer llamado a la Base de datos
@Dao
interface CuentaDao{

    @Query("SELECT * FROM cuentas ORDER BY fechaCuenta DESC")
    fun listaCompleta(): LiveData<List<Cuentas>>

    @Insert
    suspend fun Insertar (cuenta: Cuentas)

    @Update
    suspend fun update(cuenta: Cuentas)
}

