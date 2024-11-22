package com.example.myapplication.framework.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.myapplication.data.network.model.HistoricalEvent
import com.example.myapplication.databinding.ItemHistoricalEventBinding
import com.example.myapplication.framework.viewholders.HistoricalEventViewHolder

/**
 * Adaptador para mostrar una lista de eventos históricos en un RecyclerView.
 */
class HistoricalEventAdapter :
    ListAdapter<HistoricalEvent, HistoricalEventViewHolder>(HistoricalEventDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoricalEventViewHolder {
        val binding = ItemHistoricalEventBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return HistoricalEventViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoricalEventViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

/**
 * Callback para calcular las diferencias entre dos listas de eventos históricos.
 */
class HistoricalEventDiffCallback : DiffUtil.ItemCallback<HistoricalEvent>() {
    override fun areItemsTheSame(oldItem: HistoricalEvent, newItem: HistoricalEvent): Boolean {
        return oldItem.objectId == newItem.objectId
    }

    override fun areContentsTheSame(oldItem: HistoricalEvent, newItem: HistoricalEvent): Boolean {
        return oldItem == newItem
    }
}
