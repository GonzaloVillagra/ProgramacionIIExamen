package com.example.examenapp.vistas

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.examenapp.R
import com.example.examenapp.dbDao.CuentaDao
import com.example.examenapp.dbDao.Cuentas

//repositorio de la lista

class CuentaRepository(cuentaDao: CuentaDao) {
    val cuentas: LiveData<List<Cuentas>> = cuentaDao.listaCompleta()
}

class CuentaRV(var cuentas: List<Cuentas>) : RecyclerView.Adapter<CuentaHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cuentalista, parent, false)
        return CuentaHolder(itemView)
    }
     // Código para enlace Viewholder
    override fun onBindViewHolder(holder: CuentaHolder, position: Int) {
        val cuenta = cuentas[position]
        val tipoIcono: Drawable? = obtenerIcon(holder.itemView.context, cuenta.cuentaTipo)

        holder.imgIcono.setImageDrawable(tipoIcono)
        holder.bind(cuenta)
    }

    //Codigo para obtercion de iconos
    private fun obtenerIcon(context: Context, cuentaTipo: String): Drawable? {
        return when (cuentaTipo) {
            "AGUA" -> ContextCompat.getDrawable(context, R.drawable.ic_water)
            "LUZ" -> ContextCompat.getDrawable(context, R.drawable.ic_luz)
            "GAS" -> ContextCompat.getDrawable(context, R.drawable.ic_gas)
            else -> null
        }
    }
    override fun getItemCount(): Int = cuentas.size}

    class CuentaList(
        private val nuevaLista: List<Cuentas>,
        private val listaVieja: List<Cuentas>
    ): DiffUtil.Callback() {
        override fun getOldListSize(): Int = listaVieja.size
        override fun getNewListSize(): Int = nuevaLista.size
        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return listaVieja[oldItemPosition].id == nuevaLista[newItemPosition].id}
        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return listaVieja[oldItemPosition] == nuevaLista[newItemPosition]
    }
}