package jc.dam.damiandice

import android.content.Context

interface InterfazControlador {
    fun actualizarRecord(context: Context, nuevoRecord: Int, nuevaFecha: String)

    fun obtenerRecord(context: Context): Int

    fun obtenerFechaRecord(context: Context): String
}