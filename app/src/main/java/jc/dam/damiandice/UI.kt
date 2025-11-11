package jc.dam.damiandice

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.get
import androidx.compose.material3.Text
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun IU() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "RECORD: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Botones(onColorClick = { color -> Log.d("Juego", "¡Has pulsado: ${color.txt}!") })
    }
}

    @Composable
    fun Botones(onColorClick: (Datos.Colores) -> Unit) {
        Column(
            modifier = Modifier.Companion
                .size(320.dp, 320.dp)
        ) {
            val colores = Datos.Colores.entries

            Row(modifier = Modifier.Companion.weight(1f)) {
                // VERDE (Índice 0)
                Boton(
                    enum_color = colores[0],
                    onClickAction = { onColorClick(colores[0]) },
                    modifier = Modifier.Companion.weight(1f)
                )
                // ROJO (Índice 1)
                Boton(
                    enum_color = colores[1],
                    onClickAction = { onColorClick(colores[1]) },
                    modifier = Modifier.Companion.weight(1f)
                )
            }

            Row(modifier = Modifier.Companion.weight(1f)) {
                // AMARILLO (Índice 2)
                Boton(
                    enum_color = colores[2],
                    onClickAction = { onColorClick(colores[2]) },
                    modifier = Modifier.Companion.weight(1f)
                )
                // AZUL (Índice 3)
                Boton(
                    enum_color = colores[3],
                    onClickAction = { onColorClick(colores[3]) },
                    modifier = Modifier.Companion.weight(1f)
                )
            }
        }
    }

    @Composable
    fun Boton(
        enum_color: Datos.Colores,
        modifier: Modifier = Modifier.Companion,
        onClickAction: () -> Unit
    ) {
        Button(
            shape = RectangleShape,

            colors = ButtonDefaults.buttonColors(containerColor = enum_color.color),
            onClick = onClickAction,

            modifier = modifier
                .fillMaxSize()
                .padding(all = 8.dp)
        ) {
            Text(text = enum_color.txt, fontSize = 20.sp)
        }
    }


@Preview(showBackground = true)
@Composable
fun IUPreview(){
    IU()
}