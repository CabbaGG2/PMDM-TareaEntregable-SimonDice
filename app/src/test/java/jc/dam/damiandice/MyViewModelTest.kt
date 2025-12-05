package jc.dam.damiandice

import android.app.Application
import android.content.SharedPreferences
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import dam.pmdm.preferencias.ControllerPreference
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.anyInt
import org.mockito.Mockito.anyString
import org.mockito.Mockito.eq
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MyViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var application: Application

    @Mock
    private lateinit var sharedPreferences: SharedPreferences

    @Mock
    private lateinit var editor: SharedPreferences.Editor

    private lateinit var viewModel: MyViewModel

    @Before
    fun setUp() {
        // Reset singletons to avoid state pollution between tests
        Datos.secuenciaMaquina.clear()
        Datos.secuenciaJugador.clear()
        Datos.victorias.value = 0
        Datos.derrotas.value = 0
        Datos.isPrinted.value = false
        RondasSuperadas.record.value = 0

        `when`(application.getSharedPreferences(anyString(), anyInt())).thenReturn(sharedPreferences)
        `when`(sharedPreferences.edit()).thenReturn(editor)
        `when`(editor.putInt(anyString(), anyInt())).thenReturn(editor)
        `when`(editor.putString(anyString(), anyString())).thenReturn(editor)

        `when`(sharedPreferences.getInt("record", 0)).thenReturn(0)

        viewModel = MyViewModel(application)
    }

    @Test
    fun `crearRandom should update state and sequence`() {
        // When
        viewModel.crearRandom()

        // Then
        assert(viewModel.estadoLiveData.value == Estados.GENERANDO)
        assert(Datos.secuenciaMaquina.size == 1)
        assert(Datos.secuenciaJugador.isEmpty())
        assert(!Datos.isPrinted.value)
    }

    @Test
    fun `comprobar should handle correct input`() {
        // Given
        viewModel.crearRandom()
        val correctInput = Datos.secuenciaMaquina[0]

        // When
        viewModel.comprobar(correctInput)

        // Then
        assert(viewModel.estadoLiveData.value == Estados.GENERANDO)
        assert(Datos.victorias.value == 1)
        assert(Datos.secuenciaMaquina.size == 2)
    }

    @Test
    fun `comprobar should handle incorrect input`() {
        // Given
        viewModel.crearRandom()
        val incorrectInput = (Datos.secuenciaMaquina[0] + 1) % 4

        // When
        viewModel.comprobar(incorrectInput)

        // Then
        assert(viewModel.estadoLiveData.value == Estados.ERROR)
        assert(Datos.derrotas.value == 1)
        assert(Datos.victorias.value == 0)
    }

    @Test
    fun `reiniciarJuego should reset game state`() {
        // Given
        viewModel.crearRandom()
        viewModel.comprobar(0) // Simulate a game action

        // When
        viewModel.reiniciarJuego()

        // Then
        assert(viewModel.estadoLiveData.value == Estados.INICIO)
        assert(Datos.secuenciaMaquina.isEmpty())
        assert(Datos.secuenciaJugador.isEmpty())
        assert(!Datos.isPrinted.value)
    }

    @Test
    fun `esRecord should update record when new record is achieved`() {
        // Given
        `when`(sharedPreferences.getInt("record", 0)).thenReturn(5)
        val newRecord = 10

        // When
        viewModel.esRecord(newRecord)

        // Then
        verify(editor).putInt(anyString(), eq(newRecord))
        verify(editor).putString(anyString(), anyString())
        verify(editor).apply()
        assert(RondasSuperadas.record.value == newRecord)
    }

    @Test
    fun `esRecord should not update record when new record is not achieved`() {
        // Given
        `when`(sharedPreferences.getInt("record", 0)).thenReturn(10)
        val newScore = 5

        // When
        viewModel.esRecord(newScore)

        // Then
        verify(editor, never()).putInt(anyString(), anyInt())
        assert(RondasSuperadas.record.value == 10)
    }

    @Test
    fun `obtenerRecord should return the current record`() {
        // Given
        val expectedRecord = 15
        `when`(sharedPreferences.getInt("record", 0)).thenReturn(expectedRecord)

        // When
        val actualRecord = viewModel.obtenerRecord()

        // Then
        assert(actualRecord == expectedRecord)
        assert(RondasSuperadas.record.value == expectedRecord)
    }
}
