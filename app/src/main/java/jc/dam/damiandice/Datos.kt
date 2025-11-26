package jc.dam.damiandice

import androidx.compose.ui.graphics.Color
import kotlinx.coroutines.flow.MutableStateFlow

/**
 * Clase para almacenar los datos del juego
 */
object Datos {

    var secuenciaMaquina = mutableListOf<Int>()

    var secuenciaJugador = mutableListOf<Int>()
    val victorias = MutableStateFlow(0)
    var derrotas = MutableStateFlow(0)

    val rondasSuperadas = MutableStateFlow(0)

    val isPrinted = MutableStateFlow(false)

}


/**
 * Colores utilizados
 */
enum class Colores (val id: Int, val color: Color, val color_suave: Color = Color.Transparent , val txt: String){
    CLASE_ROJO(0,color = Color.Red, color_suave = Color(0xFFB71C1C), txt = "rojo"),
    CLASE_VERDE(1,color = Color.Green, color_suave = Color(0xFF1B5E20), txt = "verde"),
    CLASE_AZUL(2,color = Color.Blue, color_suave = Color(0xFF0D47A1), txt = "azul"),
    CLASE_AMARILLO(3,color = Color.Yellow, color_suave = Color(0xFFF57F17), txt = "amarillo"),
    CLASE_START(4,color = Color.Transparent, color_suave = Color.Red, txt = "Start")

}


/**
 * Estados del juego
 */
enum class Estados(val start_activo: Boolean, val boton_activo: Boolean, val colorearSecuencia: Boolean) {
    INICIO(start_activo = true, boton_activo = false, colorearSecuencia = false),
    ESPERANDO(start_activo = false, boton_activo = false, colorearSecuencia = false),
    GENERANDO(start_activo = false, boton_activo = false, colorearSecuencia = true),
    ADIVINANDO(start_activo = false, boton_activo = true, colorearSecuencia = false),

    RECORD(start_activo = false, boton_activo = true, colorearSecuencia = false),
    ERROR (start_activo = false, boton_activo = false, colorearSecuencia = false)

}