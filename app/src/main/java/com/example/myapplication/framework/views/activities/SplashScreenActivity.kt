package com.example.myapplication.framework.views.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.databinding.ActivitySplashscreenBinding
import com.example.myapplication.framework.viewmodel.SplashscreenViewModel

/**
 * Actividad que muestra una pantalla de bienvenida al iniciar la aplicación.
 */
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashscreenBinding
    private val viewModel: SplashscreenViewModel by viewModels()

    /**
     * Método llamado al crear la actividad. Inicializa los componentes y comienza el proceso de carga.
     *
     * @param savedInstanceState Estado previamente guardado de la actividad.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initializeBinding()

        initializeObservers()

        viewModel.onCreate()
    }

    /**
     * Inicializa el binding de la vista y establece el contenido de la actividad.
     */
    private fun initializeBinding() {
        binding = ActivitySplashscreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Configura los observadores para detectar cuando finaliza la carga.
     */
    private fun initializeObservers() {
        viewModel.finishedLoading.observe(this) { finishedLoading ->
            if (finishedLoading) {
                goToMain()
            }
        }
    }

    /**
     * Navega a la actividad principal y finaliza la actividad de splash screen.
     */
    private fun goToMain() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}
