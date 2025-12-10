package jc.dam.damiandice

import android.app.Application
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class ControllerSqlite(context: Context) :
    SQLiteOpenHelper(context, "puntuaciones.db", null, 1) {

        override fun onCreate(db: SQLiteDatabase) {
            db.execSQL(
                "CREATE TABLE puntuaciones(" +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "puntuacion INTEGER, " +
                        "fecha TEXT)"
            )
        }

        override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
            db.execSQL("DROP TABLE IF EXISTS puntuaciones")
            onCreate(db)
        }

        fun insertarPuntuacion(puntuacion: Int, fecha: String): Boolean {
            val db = writableDatabase
            val values = ContentValues().apply {
                put("puntuacion", puntuacion)
                put("fecha", fecha)
            }
            val res = db.insert("puntuaciones", null, values)
            return res != -1L
        }

    fun obtenerRecord(): Int {
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT MAX(puntuacion) FROM puntuaciones", null)

        var record = 0

        if (cursor.moveToFirst()) {
            if (!cursor.isNull(0)) {
                record = cursor.getInt(0)
            }
        }

        cursor.close()
        return record
    }


    fun obtenerPuntuaciones(): List<String> {
            val lista = mutableListOf<String>()
            val db = readableDatabase
            val cursor = db.rawQuery("SELECT * FROM puntuaciones", null)

            if (cursor.moveToFirst()) {
                do {
                    val p = cursor.getInt(cursor.getColumnIndexOrThrow("puntuacion"))
                    val f = cursor.getString(cursor.getColumnIndexOrThrow("fecha"))
                    lista.add("Puntuación: $p - Fecha: $f")
                } while (cursor.moveToNext())
            }
            cursor.close()
            return lista
        }
}