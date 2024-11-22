package com.example.myapplication.domain

import com.example.myapplication.data.network.model.HistoricalEvent

/**
 * Manejo de la solicitud de eventos históricos desde el repositorio.
 */
class HistoricalRequirement {

    private val repository = HistoricalRepository()

    /**
     * Invoca la obtención de eventos históricos para una página específica.
     *
     * @param page Número de página para la paginación de resultados.
     * @return Lista de [HistoricalEvent] o null si ocurre algún error.
     */
    suspend operator fun invoke(page: Int): List<HistoricalEvent>? {
        return repository.getHistoricalEvents(page)
    }
}
