package com.example.myapplication.data.network.model

import com.google.gson.annotations.SerializedName

/**
 * Representa un evento histórico con sus detalles.
 *
 * @property date Fecha del evento.
 * @property description Descripción del evento.
 * @property lang Idioma en el que está escrita la descripción.
 * @property category1 Primera categoría asociada al evento.
 * @property category2 Segunda categoría asociada al evento.
 * @property granularity Nivel de detalle de la fecha del evento.
 * @property objectId Identificador único del evento.
 */
data class HistoricalEvent(
    @SerializedName("date") val date: String?,
    @SerializedName("description") val description: String?,
    @SerializedName("lang") val lang: String?,
    @SerializedName("category1") val category1: String?,
    @SerializedName("category2") val category2: String?,
    @SerializedName("granularity") val granularity: String?,
    @SerializedName("objectId") val objectId: String?
)
