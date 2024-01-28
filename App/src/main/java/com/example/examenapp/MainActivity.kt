package com.example.examenapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examenapp.vistas.CuentaList
import com.example.examenapp.vistas.CuentaRV
import com.example.examenapp.vistas.CuentaRepository
import com.example.examenapp.vistas.CuentalistaVM
import com.example.examenapp.vistas.CuentasViewModelFactory


class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: CuentalistaVM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Codigo para obtencion de instancia del CuentaDao, crea el repository y el ViewModel
        val cuentaDao = (applicationContext as App).cuentaDao
        val repository = CuentaRepository(cuentaDao)
        val viewModelFactory = CuentasViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(CuentalistaVM::class.java)

        // Codigo conf RV
        val recyclerView = findViewById<RecyclerView>(R.id.listaCuentasRV)
        recyclerView.layoutManager = LinearLayoutManager(this)


        val adapter = CuentaRV(emptyList())
        recyclerView.adapter = adapter

        // Codigo actualizacion RV
        viewModel.cuentas.observe(this) { cuentas ->
            val oldList = adapter.cuentas
            adapter.cuentas = cuentas
            val diffResult = DiffUtil.calculateDiff(CuentaList(oldList, cuentas))
            diffResult.dispatchUpdatesTo(adapter)
        }

        // Codigo boton para ir a la actividad de agregar cuenta
        findViewById<Button>(R.id.agregarCuentaRVBTN).setOnClickListener {
            startActivity(Intent(this, AgregarCuenta::class.java))
        }
    }
}
