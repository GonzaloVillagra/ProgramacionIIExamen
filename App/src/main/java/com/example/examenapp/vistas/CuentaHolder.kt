package com.example.examenapp.vistas

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examenapp.R
import com.example.examenapp.dbDao.Cuentas

//codigo para la creacion de RecyclerView
class CuentaHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tipoCuentaTextView:  TextView = itemView.findViewById(R.id.tipoCuentaTV)
    private val fechaCuentaTextView: TextView = itemView.findViewById(R.id.fechaTV)
    private val valorCuentaTextView: TextView = itemView.findViewById(R.id.valorTV)
    val imgIcono : ImageView = itemView.findViewById(R.id.iconoTipo)

    fun bind(cuenta : Cuentas){
        fechaCuentaTextView.text = cuenta.fechaCuenta.toString()
        valorCuentaTextView.text = cuenta.cuentaValor
        tipoCuentaTextView.text = cuenta.cuentaTipo
    }
}