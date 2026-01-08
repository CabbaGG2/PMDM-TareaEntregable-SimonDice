package jc.dam.damiandice

import android.app.Application
import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dam.pmdm.preferencias.ControllerPreference
import jc.dam.damiandice.ControllerSqlite
import java.time.LocalDateTime
import kotlin.collections.plusAssign
import jc.dam.damiandice.Datos
import jc.dam.damiandice.Estados
import jc.dam.damiandice.RondasSuperadas
import jc.dam.damiandice.db.RecordDB
import kotlinx.coroutines.launch
import jc.dam.damiandice.db.entity.Record

class MyViewModel(application: Application): AndroidViewModel(application) {

    // etiqueta para logcat
    private val TAG_LOG = "miDebug"

    // estados del juego
    // usamos LiveData para que la IU se actualice
    // patron de diseño observer
    var estadoLiveData: MutableLiveData<Estados> = MutableLiveData(Estados.INICIO)

    private val recordDao = RecordDB.getDatabase(application).recordDao()


    // este va a ser nuestra lista para la secuencia random
    // usamos mutable, ya que la queremos modificar
    var _numbers = mutableStateOf(0)



    // inicializamos variables cuando instanciamos
    init {
        // estado inicial
        Log.d(TAG_LOG, "Inicializamos ViewModel - Estado: ${estadoLiveData.value}")
        actualizarRecordDesdeDB()
    }

    /**
     * crear entero random
     */
    fun crearRandom() {
        // cambiamos estado, por lo tanto la IU se actualiza

        Datos.secuenciaJugador.clear()
        Datos.isPrinted.value = false

        estadoLiveData.value = Estados.ESPERANDO
        _numbers.value = (0..3).random()
        Datos.secuenciaMaquina.add(_numbers.value)
        Log.d(TAG_LOG, "creamos random ${_numbers.value} - Estado: ${estadoLiveData.value}")
        Log.d(TAG_LOG, "Nueva secuencia: ${Datos.secuenciaMaquina}")

        estadoLiveData.value = Estados.GENERANDO
    }


    /**
     * comprobar si el boton pulsado es el correcto
     * @param ordinal: Int numero de boton pulsado
     * @return Boolean si coincide TRUE, si no FALSE
     */
    fun comprobar(ordinal: Int) {

        Datos.secuenciaJugador.add(ordinal)
        val index = Datos.secuenciaJugador.lastIndex

        Log.d(TAG_LOG, "comprobamos - Estado: ${estadoLiveData.value}")
        if (Datos.secuenciaJugador[index] != Datos.secuenciaMaquina[index]) {
            Log.d(TAG_LOG, "Fallo en la posición $index")

            Datos.derrotas.value ++
            esRecord(Datos.victorias.value)
            //llamamos a esRecord cuando perdamos para verificar si se supero el record
            Log.d(TAG_LOG, "Ronda alcanzada: ${Datos.victorias.value}")
            estadoLiveData.value = Estados.ERROR

            Log.d(TAG_LOG, "PERDIMOS - Estado: ${estadoLiveData.value}")

            return
        }

        if(Datos.secuenciaJugador.size < Datos.secuenciaMaquina.size){
            Log.d(TAG_LOG,"correcto pero faltan pulsos")
            return
        }

        Datos.victorias.value++
        esRecord(Datos.victorias.value)
        Log.d(TAG_LOG, "Ronda completada. Victorias: ${Datos.victorias.value}")

        crearRandom()
    }
    // Función para que la pantalla de error pueda volver al inicio
    fun reiniciarJuego(){
        Log.d(TAG_LOG, "Reiniciando el juego.")

        Datos.secuenciaMaquina.clear()
        Datos.secuenciaJugador.clear()
        Datos.isPrinted.value = false
        Datos.victorias.value = 0

        estadoLiveData.value = Estados.INICIO
    }

    /**
     * Se actualizó el método esRecord para utilizar Room
     */
    fun esRecord(posibleRecord: Int) {
        //Usamos viewmodelScope porque el DAO tiene funciones 'suspend'

        viewModelScope.launch {
            val recordActual = recordDao.getRecord()?.score ?: 0

            if (posibleRecord > recordActual) {
                val nuevoRecord = Record(
                    score = posibleRecord,
                    time = LocalDateTime.now().toString(),
                    timestamp = System.currentTimeMillis()
                )
                recordDao.insert(nuevoRecord)

                //metodo antiguo
                //db.insertarPuntuacion(posibleRecord, LocalDateTime.now().toString())
                RondasSuperadas.record.value = posibleRecord
                Log.d("_ROOM", "Es record: $posibleRecord")
            } else {
                Log.d("_ROOM", "No es record")
            }
        }

    }

    /**
     * Función auxiliar para cargar el récord al iniciar el ViewModel
     */
    private fun actualizarRecordDesdeDB() {
        viewModelScope.launch {
            val recordEntity = recordDao.getRecord()
            val valorRecord = recordEntity?.score ?: 0
            RondasSuperadas.record.value = valorRecord
            Log.d("_ROOM", "Record cargado de la BD: $valorRecord")
        }
    }


    /**
     * Obtiene el record actual.
     * @return El record actual.
     */
    /*fun obtenerRecord(): Int {
        RondasSuperadas.record.value = db.obtenerRecord()
        Log.d("_PREF", "Record: ${(RondasSuperadas.record.value)}")
        return RondasSuperadas.record.value
    }*/

}