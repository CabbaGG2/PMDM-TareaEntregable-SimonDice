package jc.dam.damiandice

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.collections.get
import androidx.compose.material3.Text
import androidx.compose.runtime.ComposableOpenTarget
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.LocalLifecycleOwner
import kotlinx.coroutines.delay

@Composable
fun IU(miViewModel: MyViewModel) {
    Column(
        modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "RECORD: ",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Column {
            Row {
               //creamos el boton rojo
                Boton(miViewModel, Colores.CLASE_ROJO)

                //creamos el boton verde
                Boton(miViewModel, Colores.CLASE_VERDE)
            }
            Row {
                //creamos el boton azul
                Boton(miViewModel, Colores.CLASE_AZUL)

                //creamos el boton amarillo
                Boton(miViewModel, Colores.CLASE_AMARILLO)
            }
        }
        //Aqui colocamos el boton Start
        Boton_Start(miViewModel, Colores.CLASE_START)
    }
}


    @Composable
    fun Boton(miViewModel: MyViewModel, enum_color: Colores) {

        //para buscar la etiqueta log mas facil
        val TAG_LOG = "miDebug"

        //variable para rastrear el estado del boton
        var _activo by remember { mutableStateOf(miViewModel.estadoLiveData.value!!.boton_activo) }

        miViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
            // Log.d(TAG_LOG, "Observer Estado: ${miViewModel.estadoLiveData.value!!.name}")
            _activo = miViewModel.estadoLiveData.value!!.boton_activo
        }

        //Separador entre los botones
        Spacer(modifier = Modifier.size(10.dp))

        Button(
            enabled = _activo,
            shape = RectangleShape,

            colors = ButtonDefaults.buttonColors(containerColor = enum_color.color),
            onClick = {
                Log.d(TAG_LOG, "Dentro del boton: ${enum_color.ordinal}")
                miViewModel.comprobar(enum_color.ordinal)
            },

            modifier = Modifier
                .size(120.dp,120.dp)
                .padding(all = 8.dp)
        ) {
            Text(text = "")
        }
    }

    @Composable
    fun Boton_Start(miViewModel: MyViewModel, enum_color: Colores){

        //Etiqueta facil de log
        val TAG_LOG = "miDebug"

        //Variable para el estado del boton
        var _activo by remember { mutableStateOf(miViewModel.estadoLiveData.value!!.start_activo) }

        //variable para el color utilizado en el LaunchedEffect
        var _color by remember { mutableStateOf(enum_color.color) }

        miViewModel.estadoLiveData.observe(LocalLifecycleOwner.current) {
            // Log.d(TAG_LOG, "Observer Estado: ${miViewModel.estadoLiveData.value!!.name}")
            _activo = miViewModel.estadoLiveData.value!!.start_activo
        }

        // cremos el efecto de parpadear con Launchedffect
        // mientras el estado es INICIO el boton start parpadea
        // si cambia _activo, el LaunchedEffect se inicia o se para

        LaunchedEffect(_activo) {
            Log.d(TAG_LOG, "LaunchedEffect - Estado: ${_activo}")
            //solo entra aqui si el boton esta activo = true
            while(_activo){
                _color = enum_color.color_suave
                delay(100)
                _color = enum_color.color
                delay(500)
            }
        }

        //separador entre botones
        Spacer(modifier = Modifier.size(40.dp))
        Button(
            enabled = _activo,
            //utilizamos el color del enum
            colors = ButtonDefaults.buttonColors(_color),
            onClick = {
                Log.d(TAG_LOG, "Dentro del Start - Estado: ${miViewModel.estadoLiveData.value!!.name}")
                miViewModel.crearRandom()
            },
            modifier = Modifier
                .size(100.dp,40.dp)
        ) {
            //Utilizamos el texto del enum_color
            Text(text = enum_color.txt, fontSize = 10.sp)
        }
    }


@Preview(showBackground = true)
@Composable
fun IUPreview(){
    IU(MyViewModel())
}