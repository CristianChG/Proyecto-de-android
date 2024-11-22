package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Representa la respuesta de la consulta.
 *
 * @property result Contiene los detalles del resultado.
 */
data class HistoricalResponse(
    @SerializedName("result") val result: HistoricalResult
)

/**
 * Contiene los detalles del resultado obtenido de la consulta.
 *
 * @property code Código de respuesta del servicio.
 * @property count Número total de eventos encontrados.
 * @property page Número de la página actual en la paginación.
 * @property data Lista de eventos históricos obtenidos.
 */
data class HistoricalResult(
    @SerializedName("code") val code: Int,
    @SerializedName("count") val count: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("data") val data: List<HistoricalEvent>
)
