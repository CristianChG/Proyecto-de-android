package com.example.myapplication.domain

import com.example.myapplication.data.network.HistoricalAPIClient
import com.example.myapplication.data.network.model.HistoricalEvent

/**
 * Repositorio que actúa como intermediario entre el cliente API y la aplicación,
 * proporcionando métodos para obtener eventos históricos.
 */
class HistoricalRepository {
    private val apiClient = HistoricalAPIClient()

    /**
     * Obtiene una lista de eventos históricos de una página específica.
     *
     * @param page Número de página para la paginación de resultados.
     * @return Lista de [HistoricalEvent] o null si ocurre algún error.
     */
    suspend fun getHistoricalEvents(page: Int): List<HistoricalEvent>? {
        return apiClient.getHistoricalEvents(page)
    }
}
