package com.example.myapplication.framework.views.activities

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.framework.adapters.HistoricalEventAdapter
import com.example.myapplication.framework.viewmodel.MainViewModel

/**
 * Actividad principal que muestra una lista de eventos históricos.
 */
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private val adapter = HistoricalEventAdapter()

    /**
     * Método llamado al crear la actividad. Inicializa la vista y sus componentes.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        initializeBinding()
        initializeRecyclerView()
        initializeObservers()
        initializeListeners()
    }

    /**
     * Inicializa el objeto de enlace de vista (binding) y configura el contenido de la vista.
     */
    private fun initializeBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Configura el RecyclerView con su adaptador y layout manager.
     */
    private fun initializeRecyclerView() {
        binding.rvHistoricalEvents.layoutManager = LinearLayoutManager(this)
        binding.rvHistoricalEvents.adapter = adapter
    }

    /**
     * Configura los observadores para LiveData en el ViewModel.
     */
    private fun initializeObservers() {
        viewModel.historicalEvents.observe(this) { events ->
            if (events != null) {
                adapter.submitList(events)
                if (events.isEmpty()) {
                    Toast.makeText(
                        this,
                        "Sigue buscando, seguro encontrarás algo interesante.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "No se pudieron cargar los datos.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.error.observe(this) { error ->
            if (error) {
                Toast.makeText(
                    this,
                    "Error al cargar los datos. Intente nuevamente.",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

        viewModel.currentPage.observe(this) { page ->
            binding.btnPreviousPage.isEnabled = page > 1
        }
    }

    /**
     * Configura los listeners para los componentes de la interfaz de usuario.
     */
    private fun initializeListeners() {
        binding.svCategorySearch.setOnQueryTextListener(
            object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    viewModel.searchByQuery(newText.orEmpty())
                    return true
                }
            }
        )

        binding.btnNextPage.setOnClickListener {
            viewModel.loadNextPage()
        }

        binding.btnPreviousPage.setOnClickListener {
            viewModel.loadPreviousPage()
        }
    }
}
