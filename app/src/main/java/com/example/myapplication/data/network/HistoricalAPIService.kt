package com.example.myapplication.data.network

import com.example.myapplication.data.network.model.HistoricalResponse
import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Query

/**
 * Interfaz que define los servicios API para obtener eventos históricos.
 */
interface HistoricalAPIService {

    /**
     * Solicita una lista de eventos históricos desde el endpoint.
     *
     * @param page Número de página para la paginación.
     * @return [Response] que contiene un [HistoricalResponse] con los datos obtenidos.
     */
    @POST("functions/hello")
    suspend fun getHistoricalEvents(
        @Query("page") page: Int
    ): Response<HistoricalResponse>
}
