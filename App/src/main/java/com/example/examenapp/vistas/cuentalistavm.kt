package com.example.examenapp.vistas

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.examenapp.dbDao.Cuentas


// Codigo ViewModel para listar Cuentas
class CuentalistaVM(repository: CuentaRepository) : ViewModel() {
        val cuentas: LiveData<List<Cuentas>> = repository.cuentas
}


class CuentasViewModelFactory(private val repository: CuentaRepository) : ViewModelProvider.Factory {

    override fun <G : ViewModel> create(modelClass: Class<G>): G {
       if (modelClass.isAssignableFrom(CuentalistaVM::class.java)) {
         return CuentalistaVM(repository) as G
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

