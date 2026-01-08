package jc.dam.damiandice.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

import jc.dam.damiandice.db.entity.Record

@Database(entities = [Record::class], version = 1, exportSchema = false)
abstract class RecordDB : RoomDatabase() { // Clase que extiende de RoomDatabase y que contiene un método abstracto que devuelve un RecordDao

    abstract fun recordDao(): RecordDao // Método abstracto que devuelve un RecordDao

    companion object { // Objeto compañero que contiene un método getDatabase que devuelve una instancia de RecordDatabase
        @Volatile // Propiedad que indica que el valor de INSTANCE siempre será actualizado en todos los hilos
        private var INSTANCE: RecordDB? = null // Variable que almacena la instancia de RecordDatabase, la interrogación indica que puede ser nula

        fun getDatabase(context: Context): RecordDB { // Método que devuelve una instancia de RecordDatabase
            return INSTANCE ?: synchronized(this) { // Si INSTANCE es nulo, se sincroniza el hilo y se crea una instancia de RecordDatabase
                val instance = Room.databaseBuilder( // Se crea una instancia de RecordDatabase
                    context.applicationContext, // Se obtiene el contexto de la aplicación
                    RecordDB::class.java, // Se obtiene la clase RecordDatabase
                    "simon_dice_DB" // Se establece el nombre de la base de datos
                ).build() // Se construye la base de datos
                INSTANCE = instance // Se asigna la instancia a la variable INSTANCE
                instance // Se devuelve la instancia
            }
        }
    }
}