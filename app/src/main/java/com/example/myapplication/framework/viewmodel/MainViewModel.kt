package com.example.myapplication.framework.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.example.myapplication.data.network.model.HistoricalEvent
import com.example.myapplication.domain.HistoricalRequirement
import kotlinx.coroutines.launch

/**
 * ViewModel principal que gestiona la lógica de negocio para la vista principal.
 */
class MainViewModel : ViewModel() {

    /**
     * Obtener eventos históricos.
     */
    private val historicalRequirement = HistoricalRequirement()

    /**
     * Lista interna de eventos históricos.
     */
    private val _historicalEvents = MutableLiveData<List<HistoricalEvent>?>()

    /**
     * Lista filtrada de eventos históricos expuesta a la vista.
     */
    private val _filteredHistoricalEvents = MutableLiveData<List<HistoricalEvent>?>()
    val historicalEvents: LiveData<List<HistoricalEvent>?> get() = _filteredHistoricalEvents

    /**
     * Página actual en la paginación.
     */
    private val _currentPage = MutableLiveData(1)
    val currentPage: LiveData<Int> get() = _currentPage

    /**
     * Indica si ha ocurrido un error.
     */
    private val _error = MutableLiveData(false)
    val error: LiveData<Boolean> get() = _error

    /**
     * Inicializa el ViewModel cargando la primera página de eventos históricos.
     */
    init {
        loadHistoricalEvents(1)
    }

    /**
     * Carga los eventos históricos de una página específica.
     *
     * @param page Número de página a cargar.
     */
    private fun loadHistoricalEvents(page: Int) {
        viewModelScope.launch {
            try {
                val events = historicalRequirement(page)
                if (events != null) {
                    Log.d("VIEWMODEL", "Eventos cargados: ${events.size}")
                    _historicalEvents.postValue(events)
                    _filteredHistoricalEvents.postValue(events)
                    _currentPage.postValue(page)
                } else {
                    Log.e("VIEWMODEL", "No se pudieron cargar eventos.")
                    _error.postValue(true)
                }
            } catch (e: Exception) {
                Log.e("VIEWMODEL_EXCEPTION", "Excepción: ${e.message}")
                _error.postValue(true)
            }
        }
    }

    /**
     * Filtra los eventos históricos según una consulta de búsqueda.
     *
     * @param query Texto de búsqueda para filtrar los eventos.
     */
    fun searchByQuery(query: String) {
        val events = _historicalEvents.value
        if (events != null) {
            val filteredEvents = if (query.isEmpty()) {
                events
            } else {
                events.filter { event ->
                    (event.category1?.startsWith(query, ignoreCase = true) == true) ||
                            (event.date?.startsWith(query) == true)
                }
            }
            _filteredHistoricalEvents.postValue(filteredEvents)
        }
    }

    /**
     * Carga la siguiente página de eventos históricos.
     */
    fun loadNextPage() {
        val nextPage = (_currentPage.value ?: 1) + 1
        loadHistoricalEvents(nextPage)
    }

    /**
     * Carga la página anterior de eventos históricos si existe.
     */
    fun loadPreviousPage() {
        val previousPage = (_currentPage.value ?: 1) - 1
        if (previousPage > 0) {
            loadHistoricalEvents(previousPage)
        }
    }
}
