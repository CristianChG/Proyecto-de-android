package com.example.myapplication.data.network

import android.util.Log
import com.example.myapplication.data.network.model.HistoricalEvent

/**
 * Cliente para interactuar con el servicio API de eventos históricos.
 *
 * @property api Servicio API utilizado para obtener los eventos históricos.
 */
class HistoricalAPIClient(
    private val api: HistoricalAPIService = NetworkModuleDI.apiService
) {

    /**
     * Recupera una lista de eventos históricos desde el API.
     *
     * @param page Número de página para la paginación de resultados.
     * @return Lista de [HistoricalEvent] si la solicitud es exitosa; de lo contrario, null.
     */
    suspend fun getHistoricalEvents(page: Int): List<HistoricalEvent>? {
        return try {
            val response = api.getHistoricalEvents(page)
            if (response.isSuccessful) {
                Log.d("API_RESPONSE", "Datos recibidos: ${response.body()?.result?.data}")
                response.body()?.result?.data
            } else {
                Log.e("API_ERROR", "Error en la respuesta: ${response.errorBody()?.string()}")
                null
            }
        } catch (e: Exception) {
            Log.e("API_EXCEPTION", "Excepción al conectar: ${e.message}")
            e.printStackTrace()
            null
        }
    }
}
