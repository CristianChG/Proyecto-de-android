package com.example.myapplication.framework.viewholders

import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.data.network.model.HistoricalEvent
import com.example.myapplication.databinding.ItemHistoricalEventBinding

/**
 * ViewHolder para representar un evento histórico en un RecyclerView.
 *
 * @property binding Enlace a la vista de diseño del elemento de evento histórico.
 */
class HistoricalEventViewHolder(
    private val binding: ItemHistoricalEventBinding
) : RecyclerView.ViewHolder(binding.root) {

    /**
     * Enlaza los datos de un [HistoricalEvent] a las vistas correspondientes.
     *
     * @param event Evento histórico que se mostrará en el elemento de la lista.
     */
    fun bind(event: HistoricalEvent) {
        binding.tvDate.text = event.date ?: "N/A"
        binding.tvDescription.text = event.description ?: "N/A"
        binding.tvCategory1.text = event.category1 ?: "N/A"
        binding.tvCategory2.text = event.category2 ?: "N/A"
    }
}
