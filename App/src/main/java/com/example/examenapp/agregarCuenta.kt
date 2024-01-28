package com.example.examenapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.examenapp.dbDao.Cuentas
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class AgregarCuenta : AppCompatActivity() {

    // Variables
    private lateinit var montoCuentasET: EditText
    private lateinit var fechaET: EditText
    private lateinit var grupoCuentasRB: RadioGroup

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.rvlistacuentas)

        montoCuentasET = findViewById(R.id.montoCuentaET)
        fechaET = findViewById(R.id.fechaET)
        grupoCuentasRB = findViewById(R.id.grupoCuentasRG)

        findViewById<Button>(R.id.agregarCuentaBtn).setOnClickListener {
            guardarBaseDatos()
        }
    }
    //codigo para guardado en base de datos
    @OptIn(DelicateCoroutinesApi::class)
    private fun guardarBaseDatos() {

        val valorCuenta = montoCuentasET.text.toString()
        val fechaCuenta = fechaET.text.toString()
        val tipoCuenta =  obtenerTipo()

        if (valorCuenta.isNotEmpty() && fechaCuenta.isNotEmpty()) {
            val fechaLocalData: LocalDate? = try {
                LocalDate.parse(fechaCuenta, DateTimeFormatter.ofPattern("dd-MM-yyyy"))
            } catch (e: DateTimeParseException) {
                try {LocalDate.parse(fechaCuenta, DateTimeFormatter.ofPattern("dd/MM/yyyy"))}
                catch (e: DateTimeParseException) {
                    Toast.makeText(this,getString(R.string.formato_erroneo), Toast.LENGTH_SHORT).show()
                    return
                }
            }
            val aplicacion = application as App
            val cuentaDao = aplicacion.cuentaDao

            val cuenta = Cuentas(
                cuentaTipo = tipoCuenta,
                cuentaValor = valorCuenta,
                fechaCuenta = fechaLocalData ?: LocalDate.now())

            GlobalScope.launch(Dispatchers.IO) {
                cuentaDao.Insertar(cuenta)}
            finish()}
            else {
            Toast.makeText(this, getString(R.string.campos_vacios), Toast.LENGTH_SHORT).show()
        }
    }

        //
    private fun obtenerTipo(): String {
        return when (grupoCuentasRB.checkedRadioButtonId) {
            R.id.aguaRB -> "Agua"
            R.id.luzRB -> "Luz"
            R.id.gasRB -> "Gas"
            else -> "" }
    }
}
